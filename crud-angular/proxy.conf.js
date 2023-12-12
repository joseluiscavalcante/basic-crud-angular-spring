const PROXY_CONFIG = [
  {
    context: ['/api'],
    target: 'http://localhost:8080/',
    secure: false,
    logLevel: 'debug'
  }
]

module.exports = PROXY_CONFIG;

// Concluir a configuração em  "scripts": {"start": "ng serve --proxy-config proxy.conf.js",}
