<template>
    <div>
        <el-collapse v-model="activeNames" @change="changeCollapse" accordion>
            <el-collapse-item name="1">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <el-form label-width="80px">
                            <el-form-item>
                                <el-col :span="18"> &nbsp;</el-col>
                                <el-col :span="6">
                                    <el-button type="primary" @click="filterTaobaoData">筛选</el-button>
                                    <el-button @click="saveData">保存数据</el-button>
                                </el-col>
                            </el-form-item>
                        </el-form>
                    </div>
                    <div>
                        <el-col :span="4"> 共 {{data.length}} 条数据</el-col>
                        <el-col :span="16"> &nbsp; {{tips}} &nbsp;</el-col>
                        <el-col :span="4">
                            <el-button type="primary" @click="changeCollapse('3')">查看详情</el-button>
                        </el-col>
                        <br>
                    </div>
                </el-card>
                <template slot="title">
                    <el-tag>筛选页面</el-tag>
                </template>
            </el-collapse-item>
            <el-collapse-item name="2">
                <template slot="title">
                    <el-tag>搜索历史</el-tag>
                </template>
                <el-card shadow="hover">

                    <el-table
                            :data="hisData"
                            style="width: 100%">
                        <el-table-column
                                prop="id"
                                label="id"
                                width="60"
                                :sortable="true">
                        </el-table-column>
                        <el-table-column
                                prop="img"
                                label="图片">
                            <template slot-scope="scope">
                                <div @click="viewImg(scope.row.img)">
                                    <el-avatar shape="square" :size="100"
                                               :src="scope.row.img"></el-avatar>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="name"
                                label="商品名">
                        </el-table-column>
                        <el-table-column
                                prop="price"
                                label="平均价格"
                                :sortable="true">
                        </el-table-column>
                        <el-table-column>
                            <template slot-scope="scope">
                                <el-button-group>
                                    <el-button size="mini" type="primary" @click="viewHistory(scope.row.id)">去查看</el-button>
                                    <el-button size="mini" type="danger" @click="deleteHistory(scope.row.id)">删除</el-button>
                                </el-button-group>

                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
            </el-collapse-item>

            <el-collapse-item name="3">
                <template slot="title">
                    <el-tag>商品详情</el-tag>
                </template>
                <el-card shadow="hover">
                    <div>
                        <el-col :span="10">共 {{filterTableData(data).length}} 条</el-col>
                        <el-col :span="12">
                            <el-input v-model="filterPrice">
                                <template slot="prepend">筛选价格大于 ( 0 取消筛选):</template>
                            </el-input>
                        </el-col>
                    </div>
                    <br>
                    <el-table
                            :data="filterTableData(data)"
                            highlight-current-row
                            style="width: 100%">
                        <el-table-column
                                prop="img"
                                label="图片">
                            <template slot-scope="scope">
                                <div @click="viewImg(scope.row.img)">
                                    <el-avatar shape="square" :size="100"
                                               :src="scope.row.img"></el-avatar>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column
                                label="商品名">
                            <template slot-scope="scope">
                                <el-link :href="scope.row.goodsUrl" target="_blank">{{scope.row.name}}</el-link>
                            </template>
                        </el-table-column>

                        <el-table-column
                                prop="price"
                                label="价格"
                                :sortable="true">
                        </el-table-column>

                        <el-table-column
                                prop="dealCount"
                                label="付款人数"
                                :sortable="true">
                        </el-table-column>
                        <el-table-column
                                prop="location"
                                label="地域">
                        </el-table-column>
                        <el-table-column
                                label="店铺">
                            <template slot-scope="scope">
                                <el-link :href="scope.row.shopUrl" target="_blank">{{scope.row.shopName}}</el-link>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
            </el-collapse-item>

        </el-collapse>
    </div>
</template>

<script>
    import {sendMsg, axPost, axGet} from '../../utils'
    import swal from 'sweetalert2'
    import collect from 'collect.js'

    export default {
        name: "taobao",
        data() {
            return {
                activeNames: ['1'],
                tips: '',
                filterPrice: 0,
                data: [],
                hisData: []
            };
        },
        methods: {
            changeCollapse(id) {
                this.tips = id
                this.activeNames = ['' + id]
                if (id == 2) {
                    axGet('taobao/goods/history/list').then(res => {
                        if (res.data && res.data.data) {
                            this.hisData = res.data.data
                        }
                    })
                }
            },
            test() {
                sendMsg({type: 'test'})
            },
            viewImg(img) {
                swal.fire({
                    imageUrl: img,
                    imageWidth: 600,
                    animation: false
                })
            },
            // 保存数据
            saveData() {
                axPost('taobao/goods/upload', this.data).then(res => {
                    this.tips = JSON.stringify(res.data)
                })
            },
            // 筛选
            filterTaobaoData() {
                let _this = this
                sendMsg({type: 'taobao_filter_data'}, function (response) {
                    if (response) {
                        _this.data = response.data
                    }
                })
            },
            viewHistory(goodsId) {
                this.changeCollapse(3)
                axGet('taobao/goods/info/list', {goodsId}).then(res => {
                    if (res.data && res.data.data) {
                        this.data = res.data.data
                    }
                })
            },
            deleteHistory(goodsId) {
                swal.fire({
                    title: '确定删除吗?',
                    text: "数据将不能恢复!",
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: '确定!',
                    cancelButtonText: '取消'
                }).then((result) => {
                    if (result.isConfirmed === true) {
                        axGet('taobao/goods/history/delete', {goodsId}).then(res => {
                            if (res.data && res.data.code === 0) {
                                this.changeCollapse(2)
                            }
                        })
                    }
                })

            },

            // 筛选表格价格
            filterTableData(data) {
                if (this.filterPrice > 0) {
                    return collect(data).filter(d => {
                        return d.price > this.filterPrice

                    }).all()
                } else {
                    return data
                }

            }
        }
    }
</script>

<style scoped>

</style>