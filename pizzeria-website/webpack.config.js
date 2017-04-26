const BACKEND_API_URL = process.env.NODE_ENV === 'production' ? 'https://app-b325c1a6-237a-4e11-bdde-39f93eee7f51.cleverapps.io' : 'http://localhost:8080';
const webpack = require('webpack');

module.exports = {
    entry: './src/index.js',
    output: {
        path: './public',
        filename: 'app.bundle.js'
    },

    module: {
        loaders: [{
            test: /\.js$/,
            exclude: /node_modules/,
            loader: 'babel-loader'
        },
            {
                test: /\.html$/,
                loader: 'raw-loader'
            }
        ]
    },

    devtool: 'source-map',

    plugins: [
        new webpack.DefinePlugin({'BACKEND_API_URL': JSON.stringify(BACKEND_API_URL)})
    ]

};
