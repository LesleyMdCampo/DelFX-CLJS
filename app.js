var express = require('express');

// create express app
var app = express();

app.use(express.static('public/'));

app.listen(7001);
console.log('Web server listening on port 7001');