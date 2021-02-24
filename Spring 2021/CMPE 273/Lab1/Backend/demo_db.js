const mysql = require('mysql');
let con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "4j7gz217"
});

con.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");
});