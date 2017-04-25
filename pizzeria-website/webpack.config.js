 module.exports = {
     entry: './src/app.js',
     output: {
        path: './public',
        filename: 'app.bundle.js'
    },

    devServer: {
        port: 8000
    },

    module: {
        loaders: [
            {
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

    devtool: 'source-map'

 };