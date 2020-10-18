<template>
    <div>
        <el-collapse v-model="activeNames" accordion>
            <el-collapse-item name="1">
                <template slot="title">
                    <el-tag>筛选页面</el-tag>
                </template>
                <el-card>
                    价格 :
                    <el-input
                            placeholder="beginPrice"
                            suffix-icon="el-icon-date"
                            v-model="params.beginPrice">
                    </el-input>
                    <el-input
                            placeholder="endPrice"
                            suffix-icon="el-icon-date"
                            v-model="params.endPrice">
                    </el-input>
                    <el-button @click="filterAliData">筛选</el-button>
                    <el-button @click="saveData">保存数据</el-button>
                    <el-divider/>
                    {{tips}}
                    <el-divider/>
                    <el-table
                            :data="data"
                            style="width: 100%">
                        <el-table-column
                                prop="deal"
                                label="成交额"
                                :sortable="true">
                        </el-table-column>
                        <el-table-column
                                prop="rebuy"
                                label="复购率"
                                :sortable="true">
                        </el-table-column>
                        <el-table-column
                                prop="price"
                                label="价格"
                                :sortable="true">
                        </el-table-column>
                        <el-table-column
                                label="公司">
                            <template slot-scope="scope">
                                <el-link :href="scope.row.goodsUrl" target="_blank">{{scope.row.corp}}</el-link>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
            </el-collapse-item>
            <el-collapse-item title="其他功能" name="2">
                <div>其他功能</div>
            </el-collapse-item>

        </el-collapse>
    </div>
</template>

<script>
    import {sendMsg, axPost, axGet} from '../../utils'

    export default {
        name: "ali",
        data() {
            return {
                activeNames: ['1'],
                tips: 'xx',
                params: {
                    beginPrice: 1,
                    endPrice: 999999999
                },
                data: []
            };
        },
        methods: {
            test() {
                this.tips = 'test'
                sendMsg({type: 'test'})
            },
            saveData() {
                axPost('ali/goods/upload', this.data).then(res => {
                    _this.tips = JSON.stringify(res.data)
                }).catch(err => {
                    _this.tips = JSON.stringify(err)
                })
            },
            filterAliData() {
                this.tips = 'filter_ali_data'
                let _this = this
                sendMsg({type: 'filter_ali_data', params: _this.params}, function (response) {
                    if (response) {
                        _this.tips = JSON.stringify(response.data)
                        _this.data = response.data
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>