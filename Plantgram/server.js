// My web server
// Aurhtor: Paul Pollard Created: 11/10/2021
// 
// Modifications
//

// Set-up express
var express = require("express");
var bodyParser = require("body-parser");

// Create instance of express
const app = express();

// Set up Router
const router = express.Router();
router.use("/api/users", require("./api/users"));
router.use("/api/auth", require("./api/auth"));

router.use(bodyParser.json());

app.use(router);

// Other Variables
const PORT = 3000;

// PORT 0 - 1023: Used, special, cause errors if you try to use a nummber in this range
app.use(express.static('public'));
app.use(express.urlencoded());
app.use(express.json());

// Check to see if server starts
app.listen(PORT, (err) => {
    if (err) {
        console.log("Server failed to start");
    }
    else {
        console.log(`Server started on port ${PORT}.`)
    }
})