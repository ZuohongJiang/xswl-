<template>
    <div class="manageUser-wrapper">
        <v-hover>
            <template v-slot="{ hover }">
                <v-sheet :elevation="hover?10:6" rounded>
                    <a-tabs style="padding: 30px 30px;">
                        <a-tab-pane tab="管理酒店工作人员" key="1">
                            <div style="width: 100%; text-align: right; margin:20px 0">
                                <v-btn color="primary" @click="addManager">
                                    <v-icon left>add</v-icon>
                                    添加用户
                                </v-btn>
                            </div>
                            <a-table
                                    rowKey="id"
                                    :columns="columns1"
                                    :dataSource="managerList"
                                    bordered
                            >
                            </a-table>
                        </a-tab-pane>
                        <a-tab-pane tab="管理酒店" key="2">
                            <div style="width: 100%; text-align: right; margin:20px 0">
                                <v-btn color="primary" @click="addHotel">
                                    <v-icon left>add</v-icon>
                                    新建酒店
                                </v-btn>
                            </div>
                            <a-table
                                    rowKey="id"
                                    :columns="columns2"
                                    :dataSource="hotelList"
                                    bordered
                            >
                    <span slot="action" slot-scope="record">
                        <v-btn color="primary" small @click="showHotelManagerInfo(record)">酒店工作人员信息</v-btn>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                                title="你确定删除该酒店吗？"
                                @confirm="deleteHotel(record)"
                                @cancel="cancelDeleteHotel"
                                okText="确定"
                                cancelText="取消">
                        <v-btn color="error" small>删除酒店</v-btn>
                        </a-popconfirm>
                    </span>
                            </a-table>
                        </a-tab-pane>
                    </a-tabs>
                </v-sheet>
            </template>
        </v-hover>
        <AddManagerModal></AddManagerModal>
        <hotelManagerInfoModal></hotelManagerInfoModal>
        <AddHotelModal></AddHotelModal>
    </div>
</template>
<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import AddManagerModal from './components/addManagerModal'
    import AddHotelModal from '../hotelManager/components/addHotelModal'
    import hotelManagerInfoModal from './components/hotelManagerInfoModal'

    const columns1 = [
        {
            title: '用户id',
            dataIndex: 'id',
        },
        {
            title: '用户邮箱',
            dataIndex: 'email',
        },
        {
            title: '用户名',
            dataIndex: 'userName',
        },
        {
            title: '用户密码',
            dataIndex: 'password',
        },
        {
            title: '用户手机号',
            dataIndex: 'phoneNumber',
        },
        {
            title: '信用值',
            dataIndex: 'credit',
        },
    ];
    const columns2 = [
        {
            title: '酒店名',
            dataIndex: 'name',
        },
        {
            title: '商圈',
            dataIndex: 'bizRegion',
        },
        {
            title: '地址',
            dataIndex: 'address',
        },
        {
            title: '酒店星级',
            dataIndex: 'hotelStar'
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
    export default {
        name: 'manageHotel',
        data() {
            return {
                formLayout: 'horizontal',
                pagination: {},
                columns1,
                columns2,
                data: [],
                form: this.$form.createForm(this, {name: 'manageUser'}),
            }
        },
        components: {
            AddManagerModal,
            AddHotelModal,
            hotelManagerInfoModal,
        },
        computed: {
            ...mapGetters([
                'addManagerModalVisible',
                'managerList',
                'hotelList',
                'activeHotelInfo',
                'activeHotelId',
                'activeUserInfo',
                'activeUserId',
                'addHotelModalVisible',
                'hotelManagerInfoModalVisible',
            ])
        },
        async mounted() {
            await this.getManagerList()
            await this.getHotelList()
        },
        watch: {
            activeUserInfo: function (val, oldVal) {
            }
        },
        methods: {
            ...mapActions([
                'getHotelList',
                'getManagerList',
                'get_activeUserInfo',
                'delHotel',
            ]),
            ...mapMutations([
                'set_addManagerModalVisible',
                'set_addHotelModalVisible',
                'set_hotelManagerInfoModalVisible',
                'set_activeHotelId',
                'set_activeHotelInfo',
                'set_activeUserId',
            ]),
            addHotel() {
                this.set_addHotelModalVisible(true)
            },
            addManager() {
                this.set_addManagerModalVisible(true)
            },
            //获取要展示详情的manager的信息
            showHotelManagerInfo(record) {
                this.set_hotelManagerInfoModalVisible(true)
                let managerIdList = this.managerList.map(e => {
                    return e.id
                })
                if (managerIdList.indexOf(record.managerId) != -1) {
                    this.set_activeUserId(record.managerId);
                    this.get_activeUserInfo();
                }
                this.set_activeHotelId(record.id);
                this.set_activeHotelInfo(record);
            },
            deleteHotel(record) {
                this.delHotel(record.id)
            },
            cancelDeleteHotel(record){}
        }
    }
</script>
<style scoped lang="less">
    .manageUser-wrapper {
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
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>