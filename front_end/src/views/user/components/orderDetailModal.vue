<template>
    <a-modal
            :visible="orderDetailVisible"
            title="订单详情"
            :footer="null"
            @cancel="cancel">
        <a-card style="margin-bottom: 3%;height:90px" >
            <a-row>
                <a-col :span="13">
                    <a-tag color="red" v-if="this.orderDetail.orderState=='已撤销'">{{ this.orderDetail.orderState }}</a-tag>
                    <a-tag color="blue" v-if="this.orderDetail.orderState=='已预订'">{{ this.orderDetail.orderState }}</a-tag>
                    <a-tag color="green" v-if="this.orderDetail.orderState=='已入住'">{{ this.orderDetail.orderState }}</a-tag>
                    总金额 <font style="font-size:80%;color:rgb(0, 134, 246);margin: 0px -1%;">￥</font>
                    <font style="font-size:25px;color:rgb(0, 134, 246);margin-left:0%">{{this.orderDetail.price}}</font>
                </a-col>
                <a-col :span="11" style="margin-top: 3%">
                    <b style="color: gray" v-if="!this.orderDetail.clientName">匿名</b>
                    <b style="color: gray" v-else>{{this.orderDetail.clientName}}</b>
                </a-col>
            </a-row>
        </a-card>
        <a-card style="margin-bottom: 3%;height: 100px">
            <a-row>
                <a-col :span="13">
                    <a-timeline>
                        <a-timeline-item>{{this.orderDetail.checkInDate}}入住</a-timeline-item>
                        <a-timeline-item color="green">{{this.orderDetail.checkOutDate}}离店</a-timeline-item>
                    </a-timeline>
                </a-col>
                <a-col :span="11">
                    <b style="font-size: 17px">{{this.orderDetail.hotelName}}</b><br/>
                    <b style="color: gray" v-if="this.orderDetail.roomType=='Family'">家庭房</b>
                    <b style="color: gray" v-if="this.orderDetail.roomType=='DoubleBed'">双床房</b>
                    <b style="color: gray" v-if="this.orderDetail.roomType=='BigBed'">大床房</b>
                </a-col>
            </a-row>
        </a-card>
        <a-card>
            <a-row>
                <a-col :span="13">
                    <b>{{this.orderDetail.peopleNum}}</b> 人入住，<font v-if="this.orderDetail.haveChild">有</font><font v-else>无</font> 儿童
                </a-col>
                <a-col :span="11">
                    联系方式：<b v-if="!this.orderDetail.phoneNumber">无</b>
                    <a-tooltip @click="copy" v-else>
                        <template slot="title">
                            点击复制
                        </template>
                        <b style="color: gray">{{this.orderDetail.phoneNumber}}</b>
                    </a-tooltip>
                </a-col>
            </a-row>
        </a-card>
    </a-modal>
</template>

<script>
    import {mapGetters,mapMutations,mapActions} from 'vuex'
    import {message} from 'ant-design-vue'
    export default {
        name: "orderDetailModal",
        computed:{
            ...mapGetters([
                'orderDetailVisible',
                'orderDetail'
            ])
        },
        methods:{
            ...mapMutations([
                'set_orderDetailVisible'
            ]),
            cancel(){
                this.set_orderDetailVisible(false)
            },
            copy(){
                var _input=document.createElement("input")
                _input.value=this.orderDetail.phoneNumber
                document.body.appendChild(_input)
                _input.select()
                document.execCommand("Copy")
                message.success("复制成功！")
                document.body.removeChild(_input)
            }
        }
    }
</script>

<style scoped>

</style>