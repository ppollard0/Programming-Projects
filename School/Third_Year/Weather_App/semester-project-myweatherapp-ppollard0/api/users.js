/*  Author: Paul Pollard
    Date: 2-23-2023
    users.js
    Gives user data in server
*/

const router = require("express").Router();
const conn = require("../db");
const bcrypt = require("bcryptjs");
const jwt = require("jwt-simple");
const mysql = require("mysql");
const config = require("../configuration/config.json")

function saveLastLog(email) {
    return new Promise((resolve, reject) => {
        let qry = "update users set lastLogin = NOW() where email = ?";
        conn.query(qry, [email], (err) => {
            if (err) {
                fail = true
                reject(err)
            }
            else {
                resolve()
            }
        })
    })
}

// Add user to DB
router.post("/user", function(req, res) {
    // No Username or password sent
    
    if (!req.body.email ||  !req.body.password) {
        res.status(400).json({ error: "Missing username or password."});
        return;
    }

    // Create hash for password
    const hash = bcrypt.hashSync(req.body.password, 10);

    // Create date
    let dateCreated = Date();
    
    //Create qry
    let qry = "insert into users (email, password, lname, fname, dateCreated)";
    qry += " values (??, ??, ??, ??, ??)"
    let userData = [req.body.email, hash, req.body.lname, req.body.fname, dateCreated];

    //Send qry
    qry = mysql.format(qry, userData);
    console.log(`qry: ${qry}`);
    conn.query(qry, (err) => {
        if (err) {
            return res.status(500).json({error: err})
        }
        else {
            res.status(200).json({msg: "User Created"});
        }
    })
});

// Give token when logging in
router.post("/auth", function(req,res) {
    let fail = false

    if (!req.body.email || !req.body.password) {
        res.status(401).json({error: "No username or password"});
        return;
    }

    // Create qry
    let qry = 'select email, password from users where email=?';
    conn.query(qry, [req.body.email], (err, rows) => {
        // If the server returns an error
        if(err) {
            return res.status(500).json({error:err});
        }
        // If no user is found
        if (rows.length == 0) {
            res.status(400).json({msg: 'Invalid credentials'});
        }
        else {
            // Check User and Password
            if (rows[0].email == req.body.email && rows[0].password == req.body.password) {
                const token = jwt.encode({user: req.body.email}, config.secret);
                // Set last log date and send back token
                saveLastLog(req.body.email)
                    .then(() => res.status(200).json({token: token}))
                    .catch((err) => res.status(500).json({error: err + "Server Error"}))
            }
        }
    })
});

// route to authenticate the token
router.get("/token", (req, res) => {
    if(!req.headers["xauth"]) {
        return res.status(401).json({mag: "Missing auth header"});
    }
    try{
        const token = req.headers["xauth"];
        const decoded = simpleJwt.decode(token, config.secret);
        const decodedUser = decoded.user;

        let qry = `SELECT * FROM users WHERE email = '${decodedUser}'`;
        conn.query(qry, (err, rows) => {
            if(err) return res.status(500).json({error: err});
            if(rows.length == 0){
                return res.status(400).json({msg: "No users found"});
            }
            else{
                res.status(200).json({
                    msg: "User found",
                    fname: rows[0].fname,
                });
            } 
        })
        
    }
    catch (ex){
                res.status(401).json({error: "Invalid JWT"});
    }
});

module.exports = router