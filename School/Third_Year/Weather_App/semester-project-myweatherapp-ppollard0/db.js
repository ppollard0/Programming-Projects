/*  Author: Paul Pollard
    db.js
    connection to the mysql database
    Modification Log:
        2-23-2022 - Created
        2-28-2022 - Changed user and database
*/

const mysql = require("mysql");
const config = require("./configuration/config");

const conn = mysql.createConnection({
    host: config.host,
    user: config.user,
    password: config.password,
    database: config.database
})

module.exports = conn;