<template>
    <div id="app">
        <el-tabs type="border-card">
            <el-tab-pane label="阿里"><ali></ali></el-tab-pane>
            <el-tab-pane label="淘宝"><taobao></taobao>  </el-tab-pane>
        </el-tabs>

    </div>
</template>
<script>
    import taobao from "src/popup/page/taobao.vue";
    import ali from "src/popup/page/ali.vue";
    export default {
        name: 'app',
        components: {taobao,ali}
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