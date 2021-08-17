
// Vue3 관련 설정 파일
module.exports = {
  devServer: {
    https: true,
    port: 8083,
    open: true,
    proxy: {
      '/api/v1': {
        target: 'https://i5d107.p.ssafy.io/'
        // target: 'http://localhost:8080/'
      },
      // '/api/v1/auth': {
      //   target: 'https://localhost:8443/'
      // },
      // '/api/v1/busking': {
      //   target: 'https://localhost:8443/'
      // },
      // '/api/v1/mail': {
      //   target: 'https://localhost:8443/'
      // },
      // '/api/v1/genre': {
      //   target: 'https://localhost:8443/'
      // },
      // '/api/v1/users': {
      //   target: 'https://localhost:8443/'
      // },
    },
    historyApiFallback: true,
    hot: true
  },
  css: {
    requireModuleExtension: false // import 시에 경로에 .module 포함 안해도 됨.
  },
  transpileDependencies: [
    'element-plus'
  ],
  lintOnSave: false,
  outputDir: '../backend/src/main/resources/dist'
}
