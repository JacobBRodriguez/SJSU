// Import the require dependencies
const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const session = require('express-session');
const cookieParser = require('cookie-parser');
const cors = require('cors');
const mysql = require('mysql');

// Create mySQL connection
let con = mysql.createConnection({
    host: "localhost:3306",
    user: "root",
    password: "4J7gz217$#swag"
});

con.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");
});


// NOTE: Base functionality for user access will come from Homework Code for React HW given by class TAs

//use cors to allow cross origin resource sharing
app.use(cors({ origin: 'http://localhost:3000', credentials: true }));

//use express session to maintain session data
app.use(session({
    secret              : 'cmpe273_kafka_passport_mongo',
    resave              : false, // Forces the session to be saved back to the session store, even if the session was never modified during the request
    saveUninitialized   : false, // Force to save uninitialized session to db. A session is uninitialized when it is new but not modified.
    duration            : 60 * 60 * 1000,    // Overall duration of Session : 30 minutes : 1800 seconds
    activeDuration      :  5 * 60 * 1000
}));

app.use(bodyParser.json());

//Allow Access Control
app.use(function(req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', 'http://localhost:3000');
    res.setHeader('Access-Control-Allow-Credentials', 'true');
    res.setHeader('Access-Control-Allow-Methods', 'GET,HEAD,OPTIONS,POST,PUT,DELETE');
    res.setHeader('Access-Control-Allow-Headers', 'Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers');
    res.setHeader('Cache-Control', 'no-cache');
    next();
});

//Route to test backend functionality
app.get('/test', function(req,res){
    console.log("Got here");
    res.writeHead(200,{
        'Content-Type' : 'application/json'
    });
    console.log("Testing backend");
    res.end("Goodbye chum");

})

//start your server on port 3001
app.listen(3001);
console.log("Server Listening on port 3001");