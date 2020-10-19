<template>
    <div id="app">
        <el-tabs type="border-card" v-model="activeName">
            <el-tab-pane label="阿里(搜图)" name="ali">
                <ali></ali>
            </el-tab-pane>
            <el-tab-pane label="淘宝(搜图)" name="taobao">
                <taobao></taobao>
            </el-tab-pane>
        </el-tabs>

    </div>
</template>
<script>
    import taobao from "src/popup/page/taobao.vue";
    import ali from "src/popup/page/ali.vue";
    import {sendMsg} from '../utils'

    export default {
        name: 'app',
        components: {taobao, ali},
        data() {
            return {
                activeName: 'ali'
            };
        },
        created() {
            // 获取页面host
            let _this = this
            sendMsg({type: 'check_host'}, function (response) {
                if (response) {
                    if (response.host.includes('taobao.com')) {
                        _this.activeName = 'taobao'
                    } else if (response.host.includes('1688.com')) {
                        _this.activeName = "ali"
                    }
                }

            })
        }
    }
</script>
<style scoped>
    #app {
        width: 780px;
        height: auto;
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #333;
        background: #f5f5f5;
    }
</style>