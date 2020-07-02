<template>
    <div class="hotelList">
        <v-row>
            <v-col cols="2">
                <v-hover>
                    <template v-slot="{ hover }">
                        <v-sheet
                                :elevation="hover?24:8"
                                class="mx-auto"
                                width="250"
                        >
                            <v-list>
                                <v-list-item>
                                    <v-list-item-title>
                                        <v-icon>mdi-filter</v-icon>
                                        筛选功能
                                    </v-list-item-title>
                                </v-list-item>
                                <v-divider></v-divider>
                                <v-list-group
                                        prepend-icon="local_mall"
                                >
                                    <template v-slot:activator>
                                        <v-list-item-title>筛选商圈</v-list-item-title>
                                    </template>
                                    <v-list-item>
                                        <v-select
                                                v-model="bizRegion"
                                                @change="changeBizRegion"
                                                :items="['无','西单','新街口','夫子庙','奥体中心','江宁万达','学则路']"
                                                label="选择商圈"
                                                solo
                                                class="mt-3 mb-n3"
                                        ></v-select>
                                    </v-list-item>
                                </v-list-group>
                                <v-list-group
                                        prepend-icon="star"
                                >
                                    <template v-slot:activator>
                                        <v-list-item-title>筛选星级</v-list-item-title>
                                    </template>
                                    <v-list-item>
                                        <v-rating
                                                v-model="f_star"
                                                background-color="gray darken-1"
                                                color="yellow accent-4"
                                                dense
                                                class="mx-auto"
                                                clearable
                                                @input="changeStar($event,f_star)">
                                        </v-rating>
                                    </v-list-item>
                                </v-list-group>
                                <v-list-group
                                        prepend-icon="mdi-message-draw"
                                >
                                    <template v-slot:activator>
                                        <v-list-item-title>筛选评分</v-list-item-title>
                                    </template>
                                    <v-list-item>
                                        <v-row>
                                            <v-col cols="6">
                                                <v-text-field label="最低评分" v-model="lowerStar"
                                                              @change="changeLowerStar">
                                                </v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field label="最高评分" v-model="upperStar"
                                                              @change="changeUpperStar">
                                                </v-text-field>
                                            </v-col>
                                        </v-row>
                                    </v-list-item>
                                </v-list-group>
                                <v-list-group
                                        prepend-icon="mdi-check-decagram"
                                >
                                    <template v-slot:activator>
                                        <v-list-item-title>筛选订单</v-list-item-title>
                                    </template>
                                    <v-list-item>
                                        <v-row class="mx-auto">
                                            <v-checkbox v-model="checkOrdered" label="仅查看预定过的"
                                                        @change="changeCheckOrdered"></v-checkbox>
                                        </v-row>
                                    </v-list-item>
                                </v-list-group>
                                <v-divider></v-divider>
                                <v-list-item>
                                    <v-list-item-title>
                                        <v-icon>mdi-sort</v-icon>
                                        排序功能
                                    </v-list-item-title>
                                </v-list-item>
                                <v-divider></v-divider>
                                <v-list-group
                                        prepend-icon="mdi-sort-descending"
                                >
                                    <template v-slot:activator>
                                        <v-list-item-title>降序排序</v-list-item-title>
                                    </template>
                                    <v-list-item>
                                        <v-row class="mx-auto">
                                            <v-select
                                                    v-model="selectVal"
                                                    :items="selectOpts"
                                                    item-text="text"
                                                    item-value="val"
                                                    chips
                                                    label="选择属性"
                                                    multiple
                                                    solo
                                                    class="mt-3 mb-n3"
                                                    @change="changeList"
                                            ></v-select>
                                        </v-row>
                                    </v-list-item>
                                </v-list-group>
                            </v-list>
                        </v-sheet>
                    </template>
                </v-hover>
            </v-col>
            <v-col cols="10">
                <a-layout-content style="margin: 0 16px">
                    <a-spin :spinning="hotelListLoading">
                        <v-row>
                            <v-col cols="10">
                                <a-empty v-if="hotelList.length==0">
                                    <span slot="description"> 暂无符合条件的酒店 </span>
                                </a-empty>
                                <v-container fluid>
                                    <v-row>
                                        <v-col
                                                v-for="hotel in hotelList"
                                                :key="hotel.index"
                                                cols="3">
                                            <v-hover>
                                                <template v-slot="{ hover }">
                                                    <v-badge
                                                            :value="hover&&myOrderedHotelList.indexOf(hotel.id)!=-1"
                                                            color="orange"
                                                            content="我预定过">
                                                        <v-badge
                                                                :value="myOrderedHotelList.indexOf(hotel.id)!=-1"
                                                                bordered
                                                                top
                                                                color="orange"
                                                                dot
                                                                offset-x="13"
                                                                offset-y="13"
                                                        >
                                                            <v-card
                                                                    :elevation="hover?12:6"
                                                                    class="mx-auto"
                                                                    max-width="210"
                                                            >
                                                                <v-img
                                                                        v-bind:src="require('../../assets/house.jpg')"
                                                                        height="300px"
                                                                ></v-img>
                                                                <v-card-title class="font-weight-black text--secondary">{{hotel.name}}</v-card-title>
                                                                <v-card-subtitle class="mb-n3">
                                                                    {{hotel.address?hotel.address:'暂无地址'}}
                                                                </v-card-subtitle>
                                                                <v-row class="ml-6">
                                                                    <v-rating
                                                                            v-model="hotel.hotelStar"
                                                                            background-color="gray darken-1"
                                                                            color="yellow accent-4"
                                                                            dense
                                                                            half-increments
                                                                            readonly
                                                                            size="20"
                                                                    ></v-rating>
                                                                    <span class="text--lighten-2 caption">({{ hotel.rate }}分)</span>
                                                                </v-row>
                                                                <v-divider></v-divider>
                                                                <v-card-actions>
                                                                    <v-btn
                                                                            color="blue"
                                                                            text
                                                                            class="mx-auto"
                                                                            @click="jumpToDetails(hotel.id)"
                                                                    >
                                                                        查看更多
                                                                    </v-btn>

                                                                </v-card-actions>
                                                            </v-card>
                                                        </v-badge>
                                                    </v-badge>
                                                </template>
                                            </v-hover>
                                        </v-col>
                                    </v-row>
                                </v-container>
                            </v-col>
                            <v-col cols="2">
                                <v-combobox
                                        v-model="searchVal"
                                        :items="hotelList"
                                        item-text="name"
                                        item-value="name"
                                        clearable
                                        outlined
                                        label="搜索酒店"
                                        @change="changeList"
                                ></v-combobox>
                            </v-col>
                        </v-row>
                    </a-spin>
                </a-layout-content>
            </v-col>
        </v-row>
    </div>
</template>
<script>
    import {mapGetters, mapActions, mapMutations} from 'vuex'

    export default {
        name: 'home',
        components: {},
        data() {
            return {
                emptyBox: [{name: 'box1'}, {name: 'box2'}, {name: 'box3'}],
                dataSource: [],
                oriList: [],
                saveList: [],
                nosortList: [],
                filterOp: {},
                show: false,
                bizRegion: undefined,
                lowerStar: 0,
                upperStar: 5,
                searchVal: undefined,
                checkOrdered: false,
                f_star: 0,
                selectOpts: [{'text': '按星级', 'val': 'hotelStar'}, {'text': '按评分', 'val': 'rate'}],
                selectVal: []
            }
        },
        async mounted() {
            await this.getMyOrderedHotelList()
            await this.getHotelList()
            this.saveList = this.hotelList
        },
        computed: {
            ...mapGetters([
                'hotelList',
                'hotelListLoading',
                'myOrderedHotelList'
            ])
        },
        methods: {
            ...mapMutations([
                'set_hotelListParams',
                'set_hotelListLoading',
                'set_hotelList',
            ]),
            ...mapActions([
                'getHotelList',
                'getMyOrderedHotelList'
            ]),
            changeBizRegion() {
                const reg = {
                    "无": "Noth", "西单": "XiDan", "新街口": "XinJieKou",
                    "夫子庙": "FuZiMiao", "奥体中心": "AoTiZhongXin", "江宁万达": "JiangNingWanDa", "学则路": "XueZeLu"
                }
                this.filterOp["bizRegion"] = reg[this.bizRegion]
                this.changeList()
            },
            changeStar(test) {
                this.filterOp["f_star"] = test
                this.changeList()
            },
            changeLowerStar() {
                var value = this.lowerStar
                value = value.replace(/\s+/g, "")
                if (value)
                    this.filterOp["lowerStar"] = value
                this.changeList()
            },
            changeUpperStar() {
                var value = this.upperStar
                value = value.replace(/\s+/g, "")
                if (value)
                    this.filterOp["upperStar"] = value;
                this.changeList()
            },
            changeCheckOrdered() {
                this.filterOp["checkOrdered"] = this.checkOrdered
                this.changeList()
            },
            solveSort(data) {
                let test = data.concat([])
                test.sort((a, b) => {
                    if (this.selectVal.length === 1)
                        return b[this.selectVal[0]] - a[this.selectVal[0]]
                    else {
                        if (b[this.selectVal[0]] === a[this.selectVal[0]])
                            return b[this.selectVal[1]] - a[this.selectVal[1]]
                        else return b[this.selectVal[0]] - a[this.selectVal[0]]
                    }
                })
                return test
            },
            solveFilter(data) {
                if (this.filterOp) {
                    if ("bizRegion" in this.filterOp) {
                        if (this.filterOp["bizRegion"] != "Noth") {
                            data = data.filter(item => {
                                return item.bizRegion.indexOf(this.filterOp["bizRegion"]) != -1
                            })
                        }
                    }
                    if ("f_star" in this.filterOp) {
                        let star = this.filterOp["f_star"]
                        if (star !== 0) {
                            if (star < 3)
                                data = []
                            else {
                                data = data.filter(item => {
                                    return item.hotelStar === star
                                })
                            }
                        }
                    }
                    if ("lowerStar" in this.filterOp)
                        data = data.filter(item => {
                            return item.rate >= this.filterOp["lowerStar"]
                        })
                    if ("upperStar" in this.filterOp) {
                        data = data.filter(item => {
                            return item.rate <= this.filterOp["upperStar"]
                        })
                    }
                    if ("checkOrdered" in this.filterOp) {
                        if (this.checkOrdered)
                            data = data.filter(item => {
                                return this.myOrderedHotelList.indexOf(item.id) != -1
                            })
                    }
                }
                return data
            },
            solveSearchVal() {
                var value
                var data
                if (this.searchVal) {
                    if ((typeof this.searchVal) !== "string")
                        value = this.searchVal.name
                    else value = this.searchVal
                    data = this.saveList.filter(item => {
                        value = value.replace(/\s+/g, "").toLowerCase()
                        var s = item.name.toLowerCase()
                        return s.indexOf(value) != -1
                    })
                } else {
                    data = this.saveList
                }
                return data
            },
            changeList() {
                let pureSearch = this.solveSearchVal()
                let filterResult = this.solveFilter(pureSearch)
                if (this.selectVal.length !== 0)
                    this.set_hotelList(this.solveSort(filterResult))
                else this.set_hotelList(filterResult)
            },

            jumpToDetails(id) {
                this.$router.push({name: 'hotelDetail', params: {hotelId: id}})
            },
        }
    }
</script>
<style scoped lang="less">
    .hotelList {
        text-align: center;
        padding: 50px 0;

        .emptyBox {
            height: 0;
            margin: 10px 10px
        }

        .card-wrapper {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            flex-grow: 3;
            min-height: 800px
        }

        .card-wrapper .card-item {
            margin: 30px;
            position: relative;
            height: 188px;
        }
    }
</style>
<style>
    .global-search-wrapper {
        padding-right: 50px;
    }

    .global-search {
        width: 100%;
    }

    .global-search.ant-select-auto-complete .ant-select-selection--single {
        margin-right: -46px;
    }

    .global-search.ant-select-auto-complete .ant-input-affix-wrapper .ant-input:not(:last-child) {
        padding-right: 62px;
    }

    .global-search.ant-select-auto-complete .ant-input-affix-wrapper .ant-input-suffix button {
        border-top-left-radius: 0;
        border-bottom-left-radius: 0;
    }

</style>