<template>
    <div class="hotelList">
        <v-row>
            <v-hover>
                <template v-slot="{ hover }">
                    <v-sheet
                            :elevation="hover?24:8"
                            class="mx-auto"
                            width="250"
                    >
                        <v-list>
                            <v-list-item>
                                <v-list-item-title>筛选功能</v-list-item-title>
                            </v-list-item>

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
                                    <v-list-item-title>筛选评分</v-list-item-title>
                                </template>
                                <v-list-item>
                                    <v-row>
                                        <v-col cols="6">
                                            <v-text-field label="最低评分" v-model="lowerStar" @change="changeLowerStar">
                                            </v-text-field>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-text-field label="最高评分" v-model="upperStar" @change="changeUpperStar">
                                            </v-text-field>
                                        </v-col>
                                    </v-row>
                                </v-list-item>
                            </v-list-group>
                        </v-list>
                    </v-sheet>
                </template>
            </v-hover>
           <!-- <a-layout-sider width="230" style="background: #fff">
                <a-card style="margin-top: 0%;border:0px">
                    <b>筛选功能</b>
                </a-card>
                <a-collapse  style="margin: 5% 2% 10% 2%">
                    <a-collapse-panel key="1" header="筛选商圈" style="background: #FFFFFF;">
                        <a-select default-value="无" style="width: 120px" @change="changeBizRegion">
                            <a-select-option value="Noth">无</a-select-option>
                            <a-select-option value="XiDan">西单</a-select-option>
                            <a-select-option value="XinJieKou">新街口</a-select-option>
                            <a-select-option value="FuZiMiao">夫子庙</a-select-option>
                            <a-select-option value="AoTiZhongXin">奥体中心</a-select-option>
                            <a-select-option value="JiangNingWanDa">江宁万达</a-select-option>
                            <a-select-option value="XueZeLu">学则路</a-select-option>
                        </a-select>
                    </a-collapse-panel>
                    <a-collapse-panel key="2" header="筛选评分" style="background: #FFFFFF;">
                        <a-input-number
                                :default-value="0"
                                :min="0"
                                :max="5"
                                :formatter="value => `${value}`"
                                :parser="value => value.replace(' ', '')"
                                @change="changeLowerStar"
                                style="width: 60px;"
                        />
                        ~
                        <a-input-number
                                :default-value="5"
                                :min="0"
                                :max="5"
                                :formatter="value => `${value}`"
                                :parser="value => value.replace(' ', '')"
                                @change="changeUpperStar"
                                style="width: 60px;"
                        />
                    </a-collapse-panel>
                </a-collapse>
            </a-layout-sider>-->
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
                                            cols="3" >
                                        <v-hover>
                                            <template v-slot="{ hover }">
                                                <v-card
                                                        :elevation="hover?12:6"
                                                        class="mx-auto"
                                                        max-width="210"
                                                >
                                                    <v-img
                                                            v-bind:src="require('../../assets/house.jpg')"
                                                            height="300px"
                                                    ></v-img>
                                                    <v-card-title>
                                                        {{hotel.name}}
                                                    </v-card-title>
                                                    <v-card-subtitle class="mb-n3">
                                                        {{hotel.description}}
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
                           <!-- <v-autocomplete
                                    v-model="searchVal"
                                    :items="hotelList"
                                    clearable
                                    outlined
                                    item-text="name"
                                    item-value="name"
                                    placeholder="搜索酒店"
                                    append-outer-icon="search"
                                    @change="handleSearch"
                                    @click:append-outer="changeList"
                            ></v-autocomplete>-->
                        </v-col>
                    </v-row>
<!--                    <div class="global-search-wrapper" style="width: 200px;margin-left:85%;margin-bottom: 1%">
                        <a-auto-complete
                                class="global-search"
                                size="large"
                                style="width: 100%"
                                placeholder="搜索酒店"
                                option-label-prop="title"
                                @select="onSelect"
                                @search="handleSearch"
                        >
                            <template slot="dataSource">
                                <a-select-option v-for="it in dataSource" :key="it.name" :title="it.name">
                                    {{it.name}}
                                </a-select-option>
                            </template>
                            <a-input-search
                                    @search="changeList">
                            </a-input-search>
                        </a-auto-complete>
                    </div>-->

<!--                    <div class="card-wrapper">
                        <HotelCard :hotel="item" v-for="item in hotelList" :key="item.index"
                                   @click.native="jumpToDetails(item.id)"></HotelCard>
                        <div v-for="item in emptyBox" :key="item.name"
                             class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
                        </div>

                        <a-pagination showQuickJumper :total="hotelList.totalElements" :defaultCurrent="1"
                                      @change="pageChange"></a-pagination>
                    </div>-->

                </a-spin>
            </a-layout-content>
        </v-row>
    </div>
</template>
<script>
    import HotelCard from './components/hotelCard'
    import {mapGetters, mapActions, mapMutations} from 'vuex'

    export default {
        name: 'home',
        components: {
            /*HotelCard*/
        },
        data() {
            return {
                emptyBox: [{name: 'box1'}, {name: 'box2'}, {name: 'box3'}],
                dataSource: [],
                oriList: [],
                filterOp: {},
                show:false,
                bizRegion: undefined,
                lowerStar:0,
                upperStar:5,
                searchVal: undefined,
            }
        },
        async mounted(){
            await this.getHotelList()
            this.oriList=this.hotelList
        },
        computed: {
            ...mapGetters([
                'hotelList',
                'hotelListLoading'
            ])
        },
        methods: {
            ...mapMutations([
                'set_hotelListParams',
                'set_hotelListLoading',
                'set_hotelList',
            ]),
            ...mapActions([
                'getHotelList'
            ]),


            changeBizRegion() {
                const reg={"无":"Noth","西单":"XiDan","新街口":"XinJieKou",
                    "夫子庙":"FuZiMiao","奥体中心":"AoTiZhongXin","江宁万达":"JiangNingWanDa","学则路":"XueZeLu"}
                this.filterOp["bizRegion"] = reg[this.bizRegion]
                this.changeFilter()
            },
            changeLowerStar() {
                var value=this.lowerStar
                value = value.replace(/\s+/g,"")
                if (value)
                    this.filterOp["lowerStar"] = value
                this.changeFilter()
            },
            changeUpperStar() {
                var value=this.upperStar
                value = value.replace(/\s+/g,"")
                if (value)
                    this.filterOp["upperStar"] = value;
                this.changeFilter()
            },
            changeFilter() {
                var data = this.oriList
                if (this.filterOp) {
                    if ("bizRegion" in this.filterOp) {
                        if (this.filterOp["bizRegion"] != "Noth") {
                            data = data.filter(item => {
                                return item.bizRegion.indexOf(this.filterOp["bizRegion"]) != -1
                            })
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
                    this.set_hotelList(data)
                }
            },
            onSelect(value) {
            },
            handleSearch() {
                this.getHotelList()
                if (this.searchVal) {
                    var val=this.searchVal.name
                    this.dataSource = this.searchResult(val)
                }
                else
                    this.dataSource = this.hotelList
            },
            searchResult(query) {
                return this.hotelList.filter((item) => {
                    query=query.replace(/\s+/g,"").toLowerCase()
                    var s=item.name.toLowerCase()
                    return s.indexOf(query)!=-1
                })
            },
            changeList() {
                var value
                if(this.searchVal) {
                    if ((typeof this.searchVal) !== "string")
                        value = this.searchVal.name
                    else value = this.searchVal
                    const data = this.hotelList.filter(item => {
                        value = value.replace(/\s+/g, "").toLowerCase()
                        var s = item.name.toLowerCase()
                        return s.indexOf(value) != -1
                    })
                    this.set_hotelList(data)
                }else{
                    this.getHotelList()
                }
            },

            pageChange(page, pageSize) {
                const data = {
                    pageNo: page - 1
                }
                this.set_hotelListParams(data)
                this.set_hotelListLoading(true)
                this.getHotelList()
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