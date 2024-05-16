// Models for MongoDB

const { model } = require("mongoose");
var db = require("../db");

var User = db.model("User", {
    fname: String,
    lname: String,
    email: String,
    pass: String,
    sUAbout: String,
    role: {type: String, default: 'user'}
})

module.exports = User;