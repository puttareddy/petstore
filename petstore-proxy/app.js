var express = require('express');
var jwt = require('express-jwt');
var jwks = require('jwks-rsa');
var cors = require('cors');
var pets = require('./pets.json');
var proxy = require('express-http-proxy');
var auth0Settings = require('./auth0.json');
var app = express();
var proxy_api = process.env.API_SERVER || 'http://localhost:8080'

console.log('>>>>>>>>>porxy url>>>>>>>>>>>>>: ', proxy_api);

app.use(cors());

// Add headers
app.use(function (req, res, next) {
  
      // Website you wish to allow to connect
      res.setHeader('Access-Control-Allow-Origin', '*');
  
      // Request methods you wish to allow
      res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
  
      // Request headers you wish to allow
      res.setHeader('Access-Control-Allow-Headers', 'Authorization,X-Requested-With,content-type');
  
      // Set to true if you need the website to include cookies in the requests sent
      // to the API (e.g. in case you use sessions)
      res.setHeader('Access-Control-Allow-Credentials', true);
  
      // Pass to next layer of middleware
      next();
  });

var jwtCheck = jwt({
  secret: jwks.expressJwtSecret({
      cache: true,
      rateLimit: true,
      jwksRequestsPerMinute: 5,
      jwksUri: "https://puttareddy.auth0.com/.well-known/jwks.json"
  }),
  audience: 'https://pet.puttareddy.com/',
  issuer: "https://puttareddy.auth0.com/",
  algorithms: ['RS256']
});

/*var jwtCheck = jwt({
  secret: new Buffer(auth0Settings.secret, 'base64'),
  audience: auth0Settings.audience
});
*/
//app.use(jwtCheck);

app.use( jwtCheck, proxy(proxy_api, {
  proxyReqPathResolver: function(req) {
    return require('url').parse(req.url).path;
  }
}));

app.listen(3002, function () {
  console.log('Backend listening on port 3002!');
});