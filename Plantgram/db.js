// File: db.js
// Creates a connection to the database
var mongoose = require("mongoose");
mongoose.connect("mongodb://localhost/plantgramdb", { useNewUrlParser: true, useUnifiedTopology: true });
module.exports = mongoose;