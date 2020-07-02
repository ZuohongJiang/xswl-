<template>
    <div class="room-list">
        <div class="filter">

        </div>
        <div class="list">
            <a-table
                    rowKey="id"
                    :columns="columns"
                    :dataSource="rooms"
            >
                <span slot="price" slot-scope="text">
                    <span>￥{{ text }}</span>
                </span>
                <span slot="action" slot-scope="record">
                    <v-btn color="primary" @click="showDetail(record)">查看详情</v-btn>
                    <a-divider type="vertical"></a-divider>
                    <v-btn @click="order(record)">预定</v-btn>
                </span>
            </a-table>
        </div>
        <OrderModal></OrderModal>
        <RoomModal></RoomModal>
    </div>
</template>
<script>
    import {mapGetters, mapActions, mapMutations} from 'vuex'
    import OrderModal from './orderModal'
    import {message} from "ant-design-vue";
    import RoomModal from '../../hotelManager/components/roomModal'

    const columns = [
        {
            title: 'id',
            dataIndex: 'id',
        },
        {
            title: '房型',
            dataIndex: 'roomType',
            key: 'roomType',
        },
        {
            title: '房价',
            key: 'price',
            dataIndex: 'price',
            scopedSlots: {customRender: 'price'}
        },
        {
            title: '余量',
            dataIndex: 'curNum',
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        },
    ];
    export default {
        name: 'roomList',
        props: {
            rooms: {
                type: Array
            }
        },
        data() {
            return {
                columns,
            }
        },
        components: {
            OrderModal,
            RoomModal
        },
        computed: {
            ...mapGetters([
                'orderModalVisible',
                'roomModalVisible',
                'userInfo'
            ]),
        },
        monuted() {

        },
        methods: {
            ...mapMutations([
                'set_orderModalVisible',
                'set_currentOrderRoom',
                'set_roomDetail',
                'set_roomModalVisible'
            ]),
            ...mapActions([
                'getUserInfo'
            ]),
            order(record) {
                this.getUserInfo().then(()=>{
                    if(this.userInfo.credit>=0) {
                        this.set_currentOrderRoom(record)
                        this.set_orderModalVisible(true)
                    }
                    else
                        message.error('信用过低，无法预订！')
                })
            },
            showDetail(record) {
                if (!record.detail) {
                    message.error('暂无更多详情')
                } else {
                    var obj = JSON.parse(record.detail)
                    Object.assign(record, obj)
                    this.set_roomDetail(record)
                    this.set_roomModalVisible(true)
                }
            }
        }
    }
</script>