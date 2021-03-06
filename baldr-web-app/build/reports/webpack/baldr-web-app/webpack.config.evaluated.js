{
  mode: 'development',
  resolve: {
    modules: [
      'node_modules'
    ]
  },
  plugins: [
    ProgressPlugin {
      profile: false,
      handler: [Function: handler],
      modulesCount: 5000,
      dependenciesCount: 10000,
      showEntries: true,
      showModules: true,
      showDependencies: true,
      showActiveModules: false,
      percentBy: undefined
    },
    TeamCityErrorPlugin {}
  ],
  module: {
    rules: [
      {
        test: /\.js$/,
        use: [
          'source-map-loader'
        ],
        enforce: 'pre'
      }
    ]
  },
  entry: {
    main: [
      '/home/iliyan/ivy/repo/baldr/baldr-web-app/build/js/packages/baldr-web-app/kotlin/baldr-web-app.js'
    ]
  },
  output: {
    path: '/home/iliyan/ivy/repo/baldr/baldr-web-app/build/developmentExecutable',
    filename: [Function: filename],
    library: 'baldr-web-app',
    libraryTarget: 'umd',
    globalObject: 'this'
  },
  devtool: 'eval-source-map',
  ignoreWarnings: [
    /Failed to parse source map/
  ],
  stats: {
    warnings: false,
    errors: false
  },
  devServer: {
    historyApiFallback: true
  }
}