//Authentication for Plantgram

const bodyParser = require("body-parser");
const router = require("express").Router();
const User = require("../models/user");

router.use(bodyParser.json());

router.post('/', (req,res) => {
    console.log(`Authentication requested for ${req.body.eMail}`);
    User.find({email: {$eq: req.body.eMail}}, (err, users) => {
        if (users[0]) {
            User.find({pass: {$eq: req.body.passWrd}}, (err, users) => {
                if (users[0]) {
                    console.log(`User Authenticated.`);
                    res.status(200).json({msg: `User Authenticated.`});
                }
                else {
                    res.status(401).json({msg: `User unauthorized`})
                }
            })
        }
        else {
            res.status(401).json({msg: `User unauthorized`})
        }
    })
})

module.exports = router