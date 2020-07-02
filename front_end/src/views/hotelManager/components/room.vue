<template>
    <div>
        <a-modal
                :visible="roomVisible"
                title="房间列表"
                width="1000px"
                :footer="null"
                @cancel="cancel"
        >
            <a-table
                    rowKey="id"
                    :columns="columns"
                    :dataSource="roomList"
                    bordered>
                <span slot="action" slot-scope="text, record">
                    <a-button type="primary" @click="detail(record)">查看详情</a-button>
                    <a-divider type="vertical"></a-divider>
                    <a-button @click="edit(record)">编辑</a-button>
                    <a-divider type="vertical"></a-divider>
                    <a-popconfirm
                            title="你确定删除该房间吗？"
                            @confirm="confirmDelRoom(record.id)"
                            @cancel="cancelDelRoom"
                            okText="确定"
                            cancelText="取消"
                    >
                        <a-button type="danger">删除</a-button>
                    </a-popconfirm>
                </span>
            </a-table>
        </a-modal>
        <RoomModal></RoomModal>
        <editRoomModal></editRoomModal>
    </div>
</template>


<script>
    import {message} from 'ant-design-vue'
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import RoomModal from './roomModal'
    import editRoomModal from "./editRoomModal";


    const columns = [
        {
            title: 'id',
            dataIndex: 'id',
        },
        {
            title: '房型',
            dataIndex: 'roomType'
        },
        {
            title: '价格',
            dataIndex: 'price'
        },
        {
            title: '总量',
            dataIndex: 'total'
        },
        {
            title: '余量',
            dataIndex: 'curNum'
        },
        {
            title: '操作',
            dataIndex: 'action',
            scopedSlots: {customRender: 'action'},
        }
    ]

    export default {
        name: "room",
        data() {
            return {
                columns
            }
        },
        components: {
            RoomModal,
            editRoomModal
        },
        computed: {
            ...mapGetters([
                'roomVisible',
                'roomList',
                'roomModalVisible',
                'editRoomModalVisible'
            ])
        },
        async mounted() {
            await this.getRoomList()
        },
        methods: {
            ...mapMutations([
                'set_roomVisible',
                'set_roomModalVisible',
                'set_roomDetail',
                'set_editRoomModalVisible',
                'set_currentRoom'
            ]),
            ...mapActions([
                'getRoomList',
                'deleteRoom'
            ]),
            cancel() {
                this.set_roomVisible(false)
            },
            detail(record) {
                if (!record.detail) {
                    message.error('暂无更多详情')
                } else {
                    this.set_roomDetail(record)
                    this.set_roomModalVisible(true)
                    this.set_roomVisible(false)
                }
            },
            edit(record) {
                this.set_currentRoom(record)
                this.set_editRoomModalVisible(true)
                this.set_roomVisible(false)

            },
            cancelDelRoom(){

            },
            confirmDelRoom(roomId){
                this.deleteRoom(roomId)
            }
        }
    }
</script>

<style scoped>

</style>