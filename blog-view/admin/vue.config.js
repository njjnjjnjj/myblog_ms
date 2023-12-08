const { defineConfig } = require("@vue/cli-service");
const AutoImport = require('unplugin-auto-import/webpack');
const Components = require('unplugin-vue-components/webpack');
const {ElementPlusResolver} = require('unplugin-vue-components/resolvers');
module.exports = defineConfig({
  configureWebpack: {
    plugins: [
      AutoImport({
        resolvers: [ElementPlusResolver()]
      }),
      Components({
        resolvers: [ElementPlusResolver()]
      })
    ],
  },
  transpileDependencies: true,
  lintOnSave: false,
  devServer:{
    hot: false,
    port: 8000,
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:1108/',// 你要请求的后端接口ip+port
        changeOrigin: true,// 允许跨域，在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
        ws: true,// 开启webSocket
        pathRewrite: {
          '^/api': '',// 替换成target中的地址
        }
      }
    }
  }
});
