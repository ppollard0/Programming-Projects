/*Author: Paul Pollard
  server.js
  Server For weather App
  Manages login for users and their journals
*/

const express = require('express');
//const User = require("./models/user");

const PORT = 3000;

//Create App
const app = express();

//Testing Axios Network Error
/*var cors = require('cors');
app.use(cors());*/

//Add router
const router = express.Router();

router.use(express.json());

//code for the router routes
router.use('/api/user', require('./api/users'));
router.use('/api/data', require('./api/data'));

app.use(router);

app.listen(PORT, err => {
  if (err)
    console.log("Server failed to start")
  else
    console.log("Server started on " + PORT)
})