<template>
    <div class="room-list">
        <div class="filter">

        </div>
        <div class="list">
            <a-table
                    rowKey="{record=>record.id}"
                    :columns="columns"
                    :dataSource="rooms"
            >
                <span slot="price" slot-scope="text">
                    <span>￥{{ text }}</span>
                </span>
                <span slot="action" slot-scope="record">
                    <a-button type="primary" @click="showDetail(record)">查看详情</a-button>
                    <a-divider type="vertical"></a-divider>
                    <a-button @click="order(record)">预定</a-button>
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
            ])
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
            ...mapActions([]),
            order(record) {
                this.set_currentOrderRoom(record)
                this.set_orderModalVisible(true)
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