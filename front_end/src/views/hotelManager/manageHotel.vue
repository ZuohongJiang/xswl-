<template>
    <div class="manageHotel-wrapper">
        <v-hover>
            <template v-slot="{ hover }">
                <v-sheet
                        :elevation="hover?10:6"
                        rounded>
                    <a-tabs style="padding: 30px 30px;">
                        <a-tab-pane tab="酒店管理" key="1">
                            <a-table
                                    rowKey="id"
                                    :columns="columns1"
                                    :dataSource="manageHotelList"
                                    bordered
                            >
                                <span slot="bizRegion" slot-scope="text">
                                <span >{{reg[text]}}</span>
                    </span>
                                <span slot="hotelStar" slot-scope="text">
                                <span v-if="text=='Three'">三星级</span>
                                <span v-if="text=='Four'">四星级</span>
                                <span v-if="text=='Five'">五星级</span>
                    </span>
                    <span slot="action" slot-scope="record">
                        <v-btn color="primary" small @click="addRoom(record)">录入房间</v-btn>
                        <a-divider type="vertical"></a-divider>
                        <v-btn small @click="showRoom(record)">房间管理</v-btn>
                        <a-divider type="vertical"></a-divider>
                        <v-btn small @click="showCoupon(record)">优惠策略</v-btn>
                        <a-divider type="vertical"></a-divider>
                        <v-btn small @click="showHotelInfo(record)">酒店信息</v-btn>
                    </span>
                            </a-table>
                        </a-tab-pane>
                        <a-tab-pane tab="订单管理" key="2">
                            <a-table
                                    rowKey="id"
                                    :columns="columns2"
                                    :dataSource="orderList"
                                    bordered
                            >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                                <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                    </span>
                                <span slot="orderState" slot-scope="text">
                        <a-tag color="red" v-if="text=='已撤销'">{{ text }}</a-tag>
                        <a-tag color="blue" v-if="text=='已预订'">{{ text }}</a-tag>
                        <a-tag color="green" v-if="text=='已执行'">{{ text }}</a-tag>
                        <a-tag color="grey" v-if="text=='已评价'">{{ text }}</a-tag>
                    </span>
                                <span slot="action" slot-scope="record">
                        <v-btn color="primary" small @click="showDetail(record)">订单详情</v-btn>
                        <a-divider type="vertical" v-if="record.orderState=='已预订'"></a-divider>
                        <a-popconfirm
                                title="确认执行订单吗？"
                                @confirm="execute(record)"
                                okText="确定"
                                cancelText="取消"
                                v-if="record.orderState=='已预订'"
                        >
                            <v-btn color="success" small>执行订单</v-btn>
                        </a-popconfirm>
                    </span>
                            </a-table>
                        </a-tab-pane>
                    </a-tabs>
                </v-sheet>
            </template>
        </v-hover>
        <AddRoomModal></AddRoomModal>
        <UpdateHotelInfoModal></UpdateHotelInfoModal>
        <Coupon></Coupon>
        <OrderDetail></OrderDetail>
        <Room></Room>
    </div>
</template>
<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import AddRoomModal from './components/addRoomModal'
    import UpdateHotelInfoModal from './components/updateHotelInfoModal'
    import Coupon from './components/coupon'
    import Room from './components/room'
    import OrderDetail from '../user/components/orderDetailModal'

    const moment = require('moment')
    const columns1 = [
        {
            title: '酒店名',
            dataIndex: 'name',
        },
        {
            title: '商圈',
            dataIndex: 'bizRegion',
            scopedSlots: {customRender: 'bizRegion'},
        },
        {
            title: '地址',
            dataIndex: 'address',
        },
        {
            title: '酒店星级',
            dataIndex: 'hotelStar',
            scopedSlots: {customRender: 'hotelStar'},
        },
        {
            title: '评分',
            dataIndex: 'rate',
        },
        {
            title: '简介',
            dataIndex: 'description',
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        },
    ];
    const columns2 = [
        {
            title: '订单号',
            dataIndex: 'id',
        },
        {
            title: '酒店名',
            dataIndex: 'hotelName',
        },
        {
            title: '房型',
            dataIndex: 'roomType',
            scopedSlots: {customRender: 'roomType'}
        },
        {
            title: '入住时间',
            dataIndex: 'checkInDate',
            scopedSlots: {customRender: 'checkInDate'}
        },
        {
            title: '离店时间',
            dataIndex: 'checkOutDate',
            scopedSlots: {customRender: 'checkOutDate'}
        },
        {
            title: '入住人数',
            dataIndex: 'peopleNum',
        },
        {
            title: '房价',
            dataIndex: 'price',
        },
        {
            filters: [{text: '已预订', value: '已预订'}, {text: '已撤销', value: '已撤销'}, {
                text: '已执行',
                value: '已执行'
            }, {text: '已评价', value: '已评价'}],
            onFilter: (value, record) => record.orderState.includes(value),
            title: '订单状态',
            dataIndex: 'orderState',
            scopedSlots: {customRender: 'orderState'}
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        },
    ];
    export default {
        name: 'manageHotel',
        data() {
            return {
                formLayout: 'horizontal',
                pagination: {},
                columns1,
                columns2,
                form: this.$form.createForm(this, {name: 'manageHotel'}),
                reg : {
                    "Noth":"无",  "XiDan":"西单",  "XinJieKou":"新街口",
                    "FuZiMiao":"夫子庙",  "AoTiZhongXin":"奥体中心",  "JiangNingWanDa":"江宁万达",  "XueZeLu":"学则路"
                }
            }
        },
        components: {
            OrderDetail,
            // AddHotelModal,
            AddRoomModal,
            UpdateHotelInfoModal,
            Coupon,
            Room,
        },
        computed: {
            ...mapGetters([
                'orderList',
                'manageHotelList',
                'hotelIdList',
                'userId',
                'managerId',
                // 'addHotelModalVisible',
                'addRoomModalVisible',
                'updateHotelInfoModalVisible',
                'updateHotelInfoParams',
                'activeHotelInfo',
                'activeHotelId',
                'couponVisible',
                'roomVisible'
            ]),
        },
        async mounted() {
            await this.set_managerId(this.userId);
            await this.getHotelListByManagerId();
            await this.initHotelIdList();
            await this.getManageHotelsOrders(this.hotelIdList);
            // 订单重复BUG代码
            // for(let i=0;i<this.hotelIdList.length;i++){
            //     await this.set_activeHotelId(this.hotelIdList[i]);
            //     await this.getHotelOrders();
            // }

        },
        methods: {
            ...mapMutations([
                'set_hotelIdList',
                'set_addHotelModalVisible',
                'set_addRoomModalVisible',
                'set_updateHotelInfoModalVisible',
                'set_couponVisible',
                'set_activeHotelId',
                'set_activeHotelInfo',
                'set_roomVisible',
                'set_orderDetail',
                'set_orderDetailVisible',
                'set_managerId',
            ]),
            ...mapActions([
                'getHotelListByManagerId',
                'getManageHotelsOrders',
                'getHotelOrders',
                'getHotelCoupon',
                'annulCoupon',
                'getHotelById',
                'updateHotelInfo',
                'getRoomList',
                'deleteOrderRecord',
                'executeOrder',
                'delHotel'
            ]),
            showHotelInfo(record) {
                this.set_activeHotelId(record.id);
                this.getHotelById();
                this.set_updateHotelInfoModalVisible(true)
            },
            addRoom(record) {
                this.set_activeHotelId(record.id)
                this.set_addRoomModalVisible(true)
            },
            showCoupon(record) {
                this.set_activeHotelId(record.id)
                this.set_couponVisible(true)
                this.getHotelCoupon()
            },
            showRoom(record) {
                this.set_activeHotelId(record.id)
                this.set_roomVisible(true)
                this.getRoomList()
            },
            showDetail(record) {
                this.set_orderDetail(record)
                this.set_orderDetailVisible(true)
            },
            deleteHotel(record) {
                this.delHotel(record.id)
            },
/*            deleteOrder(record) {
                this.deleteOrderRecord(record.id)
            },*/
            //初始化当前管理员管理的酒店id列表
            initHotelIdList() {
                let tempArray = [];
                for (let i = 0; i < this.manageHotelList.length; i++) {
                    tempArray.push(this.manageHotelList[i].id);
                }
                this.set_hotelIdList(tempArray);
            },


            execute(record) {
                if (record.orderState === "已撤销") {
                    alert("订单已撤销！");
                    return;
                } else if (record.orderState === "已执行" || record.orderState === "已评价") {
                    alert("订单已执行");
                    return;
                } else {
                    this.executeOrder(record.id);
                    record.orderState = "已执行";
                }

            },
        }
    }
</script>
<style scoped lang="less">
    .manageHotel-wrapper {
        padding: 50px;

        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageHotel-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>