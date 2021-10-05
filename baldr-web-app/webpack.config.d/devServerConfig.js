// YourProject/webpack.config.d/devServerConfig.js

config.devServer = {
    ...config.devServer, // Merge with other devServer settings
    "historyApiFallback": true,
    open: 'http://localhost',
    port: 80,
};