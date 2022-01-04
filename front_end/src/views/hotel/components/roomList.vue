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
                <span slot="pic" slot-scope="id">
                    <v-img class="smallPic" v-bind:src="getHotelPicUrl(id)">
                    </v-img>
                </span>
                <span slot="price" slot-scope="text">
                    <span class="price">￥{{ text }}</span>
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
<style>
.smallPic{
    width: 50px;
    height: 50px;
}
.price{
    color: #FF6600;
    font-weight: bold;
}
</style>
<script>
    import {mapGetters, mapActions, mapMutations} from 'vuex'
    import OrderModal from './orderModal'
    import {message} from "ant-design-vue";
    import RoomModal from '../../hotelManager/components/roomModal'

    const columns = [
        {
            title: '小图',
            dataIndex: 'id',
            scopedSlots: {customRender: 'pic'}
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
                //获取用户信息，判断信用值是否允许预订
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
                console.log(record)
                if (!record.detail) {
                    message.error('暂无更多详情')
                } else {
                    var obj = JSON.parse(record.detail)
                    Object.assign(record, obj)
                    this.set_roomDetail(record)
                    this.set_roomModalVisible(true)
                }
            },
             getHotelPicUrl(id){
                return require("../../../assets/room/" + id + ".jpg")
            }
        }
    }
</script>