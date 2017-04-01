var path = require('path');

module.exports = {
  entry: './src/WebsocketJ.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, './src/dist')
  }
};