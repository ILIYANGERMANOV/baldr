let config = {
  mode: 'development',
  resolve: {
    modules: [
      "node_modules"
    ]
  },
  plugins: [],
  module: {
    rules: []
  }
};

// entry
config.entry = {
    main: ["/home/iliyan/ivy/repo/baldr/baldr-web-app/build/js/packages/baldr-web-app/kotlin/baldr-web-app.js"]
};

config.output = {
    path: "/home/iliyan/ivy/repo/baldr/baldr-web-app/build/developmentExecutable",
    filename: (chunkData) => {
        return chunkData.chunk.name === 'main'
            ? "baldr-web-app.js"
            : "baldr-web-app-[name].js";
    },
    library: "baldr-web-app",
    libraryTarget: "umd",
    globalObject: "this"
};

// source maps
config.module.rules.push({
        test: /\.js$/,
        use: ["source-map-loader"],
        enforce: "pre"
});
config.devtool = 'eval-source-map';
config.ignoreWarnings = [/Failed to parse source map/]

// Report progress to console
// noinspection JSUnnecessarySemicolon
;(function(config) {
    const webpack = require('webpack');
    const handler = (percentage, message, ...args) => {
        const p = percentage * 100;
        let msg = `${Math.trunc(p / 10)}${Math.trunc(p % 10)}% ${message} ${args.join(' ')}`;
        msg = msg.replace("/home/iliyan/ivy/repo/baldr/baldr-web-app/build/js", '');;
        console.log(msg);
    };

    config.plugins.push(new webpack.ProgressPlugin(handler))
})(config);

// noinspection JSUnnecessarySemicolon
;(function(config) {
    const tcErrorPlugin = require('kotlin-test-js-runner/tc-log-error-webpack');
    config.plugins.push(new tcErrorPlugin())
    config.stats = config.stats || {}
    Object.assign(config.stats, config.stats, {
        warnings: false,
        errors: false
    })
})(config);

// devServerConfig.js
// YourProject/webpack.config.d/devServerConfig.js

config.devServer = {
    ...config.devServer, // Merge with other devServer settings
    "historyApiFallback": true,
};


// save evaluated config file
;(function(config) {
    const util = require('util');
    const fs = require('fs');
    const evaluatedConfig = util.inspect(config, {showHidden: false, depth: null, compact: false});
    fs.writeFile("/home/iliyan/ivy/repo/baldr/baldr-web-app/build/reports/webpack/baldr-web-app/webpack.config.evaluated.js", evaluatedConfig, function (err) {});
})(config);

module.exports = config
