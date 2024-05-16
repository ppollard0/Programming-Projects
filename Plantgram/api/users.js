// User api for PlantGram

const User = require("../models/user");
const router = require("express").Router();
const bodyParser = require("body-parser");

router.use(bodyParser.json());

router.post("/", function(req, res) {

    //Create a user from the submitted form data.
    var pltFan = new User({
        fname: req.body.fname,
        lname: req.body.lname,
        email: req.body.email,
        pass: req.body.pass,
        sUAbout: req.body.sUAbout
    });
    
    pltFan.save((err, pltFan) => {
        if (err) {
            res.status(400).send(err);
        }
        else {
            res.status(201).json({msg:"Account Created"});
        }
    });
});

module.exports = router