const path = require('path');
var webpack = require('webpack');

module.exports = {
  entry: './src/rsa.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'dist')
  }
};