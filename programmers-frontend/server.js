// server.js
const express = require('express');
const app = express();

app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
    res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization');
    next();
});

// Dodaj resztę konfiguracji serwera i obsługę tras API
// ...

const port = 3000;
app.listen(port, () => {
    console.log(`Serwer nasłuchuje na porcie ${port}`);
});