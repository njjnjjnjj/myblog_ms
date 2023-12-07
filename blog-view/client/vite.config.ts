import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// 按需导入
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
// 图标
import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'


// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        AutoImport({
            resolvers: [ElementPlusResolver(),
                // Auto import icon components
                // 自动导入图标组件
                IconsResolver({
                    prefix: 'Icon',
                }),],
        }),
        Components({
            resolvers: [ElementPlusResolver(),
                // Auto register icon components
                // 自动注册图标组件
                IconsResolver({
                    enabledCollections: ['ep'],
                }),],
        }),
        Icons({
            autoInstall: true,
        }),
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
    server: {
        host:'0.0.0.0',
        port: 1109,
        proxy: {
            '/api':{
                target:"http://127.0.0.1:1108/", //跨域地址
                changeOrigin:true, //支持跨域
                rewrite:(path) => path.replace(/^\/api/, "")//重写路径,替换/api
            }
            // '/api': {
            //
            //     target: 'http://127.0.0.1:10010/core',// 你要请求的后端接口ip+port
            //     changeOrigin: true,// 允许跨域，在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
            //     ws: true,// 开启webSocket
            //     pathRewrite: (path)=>{
            //         return path.replace(/^\/api/, "");
            //         // '^/api': '',// 替换成target中的地址
            //     }
            // }
        }
    },
})
