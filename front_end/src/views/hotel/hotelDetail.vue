<template>
    <a-layout>
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title }}
                </h1>
                <div class="hotel-info">
<!--                    <a-card style="width: 240px">
                        <img
                            alt="example"
                            src="@/assets/cover.jpeg"
                            slot="cover"
                            referrerPolicy="no-referrer"
                            />
                    </a-card>-->
                    <v-card elevation="6">
                        <v-row>
                            <v-col cols="8">
                                <v-img
                                        class="white--text align-end ml-3"
                                        height="400px"
                                        v-bind:src="require('../../assets/house.jpg')"
                                >
                                    <v-card-title>{{currentHotelInfo.name}}</v-card-title>
                                </v-img>
                            </v-col>
                            <v-col cols="4">
                                <v-card-text class="mt-7 ml-n3" style="font-size:17px">
                                    <div>地址：{{currentHotelInfo.address?currentHotelInfo.address:'暂无'}}</div>
                                    <div>评分：{{currentHotelInfo.rate}}分</div>
                                    <v-row class="my-n3">
                                        <v-col cols="3" class="mr-0 pr-0">星级：</v-col>
                                        <v-col cols="9" class="ml-n5 pl-0">
                                            <v-rating
                                                    v-model="currentHotelInfo.hotelStar"
                                                    color="yellow accent-4"
                                                    background-color="gray darken-1"
                                                    dense
                                                    half-increments
                                                    readonly
                                                    size="20"></v-rating>
                                        </v-col>
                                    </v-row>
                                    <div>酒店联系方式：{{currentHotelInfo.phoneNum}}</div>
                                    <div>酒店简介：{{currentHotelInfo.description}}</div>
                                </v-card-text>

                            </v-col>
                        </v-row>
                    </v-card>
                    <div class="info">
                       <!-- <a-card>
                            <div class="items" v-if="currentHotelInfo.name">
                                <span class="label">酒店名称：</span>
                                <span class="value">{{ currentHotelInfo.name }}</span>
                            </div>
                            <div class="items" v-if="currentHotelInfo.address">
                                <span class="label">地址</span>
                                <span class="value">{{ currentHotelInfo.address }}</span>
                            </div>
                            <div class="items" v-if="currentHotelInfo.rate">
                                <span class="label">评分:</span>
                                <span class="value">{{ currentHotelInfo.rate }}</span>
                            </div>
                            <div class="items" v-if="currentHotelInfo.hotelStar">
                                <span class="label">星级:</span>
                                <a-rate style="font-size: 15px" :value="this.changeStarToNum()" disabled allowHalf/>
                            </div>
                            <div class="items" v-if="currentHotelInfo.phoneNum">
                                <span class="label">酒店联系方式:</span>
                                <span class="value">{{ currentHotelInfo.phoneNum }}</span>
                            </div>
                            <div class="items" v-if="currentHotelInfo.description">
                                <span class="label">酒店简介:</span>
                                <span class="value">{{ currentHotelInfo.description }}</span>
                            </div>
                        </a-card>-->

                    </div>
                </div>
                <a-divider></a-divider>
                <a-tabs>
                    <a-tab-pane tab="房间信息" key="1">
                        <RoomList :rooms="currentHotelInfo.rooms"></RoomList>
                    </a-tab-pane>
                </a-tabs>
            </div>
        </a-layout-content>
    </a-layout>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import RoomList from './components/roomList'
export default {
    name: 'hotelDetail',
    components: {
        RoomList,
    },
    data() {
        return {

        }
    },
    computed: {
        ...mapGetters([
            'currentHotelInfo',
        ])
    },
    mounted() {
        this.set_currentHotelId(Number(this.$route.params.hotelId))
        this.getHotelById()
    },
    beforeRouteUpdate(to, from, next) {
        this.set_currentHotelId(Number(to.params.hotelId))
        this.getHotelById()
        next()
    },
    methods: {
        ...mapMutations([
            'set_currentHotelId',
        ]),
        ...mapActions([
            'getHotelById'
        ]),
        changeStarToNum(){
            if(this.currentHotelInfo.hotelStar==='Three')
                return 3;
            else if(this.currentHotelInfo.hotelStar==='Four')
                return 4;
            else if(this.currentHotelInfo.hotelStar==='Five')
                return 5;
        }
    }
}
</script>
<style scoped lang="less">
    .hotelDetailCard {
        padding: 50px 50px;
    }
    .hotel-info {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        .info{
            padding: 10px 0;
            display: flex;
            flex-direction: column;
            margin-left: 20px;
            .items {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
                .label{
                    margin-right: 10px;
                    font-size: 18px;
                }
                .value {
                    margin-right: 15px
                }
            }
        }
    }
</style>