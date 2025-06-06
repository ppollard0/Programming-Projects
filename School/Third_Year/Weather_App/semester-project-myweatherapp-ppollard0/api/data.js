/*  Author: Paul Pollard
    Date: 4-1-2023
    data.js
    Checks Token status for expiriation & returns journal data

    API:     Path:                   Type:
    Data     /api/data/load_data     GET
    Status:
    200-Okay
    403-Forbidden
    500-Server

    Requires the token be passed as an X-Auth header
*/

const router = require("express").Router();
const conn = require("../db");
const jwt = require("jwt-simple");
const mysql = require("mysql");
const config = require("../configuration/config.json");

router.get('/load_data', (req, res) => {
    if(!req.headers["x-auth"]) {
        return res.status(401).json({mag: "Missing auth header"});
    }
    try{
        // Checks time elapsed since last login
        const token = req.headers["x-auth"];
        const decoded = jwt.decode(token, config.secret);
        const decodedUser = decoded.user;

        let qry = `SELECT lastLogin FROM users WHERE email = '${decodedUser}'`;
        conn.query(qry, (err, rows) => {
            if(err) return res.status(500).json({error: err});
            if(rows.length == 0){
                return res.status(400).json({msg: "No users found"});
            }
            else {
                // get current date & last login date
                let cur = Date.parse();
                let lastLog = rows[0].lastLogin
                console.log(rows[0].lastLogin)
                let time_delta = 5000
                let elapsed = lastLog - cur
                if (time_delta == elapsed) {
                    // Gets Journal data if token is valid
                    const num = 3
                    let qry = `select date, title, log from journal_entries where users_email = 'ppoll' order by date limit ?`
                    conn.query(qry, [num], (err, rows) => {
                        if(err) {
                            return res.status(500).json({error: err});
                        }
                        if(rows.length == 0){
                            return res.status(400).json({msg: "No users found"});
                        }
                        else {
                            res.status(200).json(rows)
                        }
                    })
                }
                else {
                    return res.status(403).json({msg: "Token Missing/Expired"})
                }
            } 
        })
        
    }
    catch (ex){
                res.status(401).json({error: "Invalid JWT"});
    }
});

// Get the contents of your journal.
router.get('/load_journal', (req, res) => {
    if(!req.body.num) {
        res.status(401).json({msg: 'No Num'})
    }
    let qry = `select date, title, log from journal_entries where users_email = 'ppoll' order by date limit ?`
    conn.query(qry, [req.body.num], (err, rows) => {
        if(err) {
            return res.status(500).json({error: err});
        }
        if(rows.length == 0){
            return res.status(400).json({msg: "No users found"});
        }
        else {
            res.status(200).json(rows)
        }
    })
})

module.exports = router