<template>
    <div class="info-wrapper">
        <v-hover>
            <template v-slot="{ hover }">
                <v-sheet
                        :elevation="hover?10:6"
                        rounded>
                    <a-tabs style="padding: 30px 30px;">
                        <a-tab-pane tab="我的信息" key="1">
                            <a-form :form="form" style="margin-top: 30px">

                                <a-form-item label="用户名" :label-col="{ span: 3 }"
                                             :wrapper-col="{ span: 8, offset: 1  }">
                                    <a-input
                                            placeholder="请填写用户名"
                                            v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名' }],initialValue: userInfo.userName}]"
                                            v-if="modify"
                                    />
                                    <span v-else>{{ userInfo.userName }}</span>
                                </a-form-item>
                                <a-form-item label="邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                                    <span>{{ userInfo.email }}</span>
                                </a-form-item>

                                <a-form-item label="手机号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                                    <a-input
                                            placeholder="请填写手机号"
                                            v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }],initialValue:userInfo.phoneNumber}]"
                                            v-if="modify"
                                    />
                                    <span v-else>{{ userInfo.phoneNumber}}</span>
                                </a-form-item>
                                <a-form-item label="信用值" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                                    <span>{{ userInfo.credit }}</span>
                                </a-form-item>
                                <a-form-item label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }"
                                             v-if="modify_password">
                                    <a-input
                                            placeholder="请输入旧密码"
                                            v-decorator="['oldPassword',{rules:[{required:true,message:'请输入'}]}]"
                                    />
                                    <a-input
                                            placeholder="请输入新密码"
                                            v-decorator="['password', { rules: [{ required: true, message: '请输入' }] }]"
                                    />
                                </a-form-item>
                                <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify||modify_password">
                                    <v-btn color="primary" @click="saveModify">
                                        保存
                                    </v-btn>
                                    <v-btn style="margin-left: 30px" @click="cancelModify">
                                        取消
                                    </v-btn>
                                </a-form-item>
                                <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else>
                                    <v-btn color="primary" @click="modifyInfo">
                                        修改信息
                                    </v-btn>
                                    <v-btn style="margin-left: 5%" color="error" @click="modifyPassword">
                                        修改密码
                                    </v-btn>
                                </a-form-item>
                            </a-form>
                        </a-tab-pane>
                        <a-tab-pane tab="我的订单" key="2">
                            <a-table
                                    rowKey="id"
                                    :columns="columns"
                                    :dataSource="userOrderList"
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
                        <v-btn color="primary" small @click="showDetail(record)">查看</v-btn>
                        <a-divider type="vertical" v-if="record.orderState == '已预订'"></a-divider>
                                    <a-popconfirm
                                            title="你确定撤销该订单吗？"
                                            @confirm="confirmCancelOrder(record.id)"
                                            @cancel="cancelCancelOrder"
                                            okText="确定"
                                            cancelText="取消">
                        <v-btn color="error" small v-if="record.orderState == '已预订'">撤销</v-btn>
                                    </a-popconfirm>
                        <a-divider type="vertical" v-if="record.orderState == '已执行'"></a-divider>
                        <v-btn color="primary" small v-if="record.orderState=='已执行'"
                               @click="beginComment(record)">评价</v-btn>
                    </span>
                            </a-table>
                        </a-tab-pane>
                    </a-tabs>
                </v-sheet>

            </template>
        </v-hover>
        <v-row justify="center">
            <v-dialog v-model="dialog" persistent max-width="700">
                <v-card>
                    <v-card-title class="headline">为本次入住撰写评价</v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-rating v-model="rating" half-increments hover></v-rating>
                            <v-alert type="error" v-if="rating===0">请选择评分！</v-alert>
                        </v-container>
                        <v-textarea outlined label="在此写下您的评价" v-model="content"></v-textarea>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="green darken-1" text @click="cancelComment()">取消</v-btn>
                        <v-btn color="green darken-1" text @click="handleSubmit()">提交</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
        <OrderDetailModal></OrderDetailModal>
    </div>
</template>
<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import OrderDetailModal from './components/orderDetailModal'


    const columns = [
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
            title: '房价',
            dataIndex: 'price',
        },
        {
            title: '状态',
            filters: [{text: '已预订', value: '已预订'}, {text: '已撤销', value: '已撤销'}, {
                text: '已执行',
                value: '已执行'
            }, {text: '已评价', value: '已评价'}],
            onFilter: (value, record) => record.orderState.includes(value),
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
        name: 'info',
        data() {
            return {
                modify_password: false,
                modify: false,
                content: '',
                comment: {},
                dialog: false,
                rating: 0,
                formLayout: 'horizontal',
                pagination: {},
                columns,
                data: [],
                form: this.$form.createForm(this, {name: 'coordinated'}),
            }
        },
        components: {
            OrderDetailModal
        },
        computed: {
            ...mapGetters([
                'userId',
                'userInfo',
                'userOrderList',
                'orderDetailVisible',
                'recordDetail'
            ]),
            filteredKeys() {
                return this.keys.filter(key => key !== `Name`)
            },
        },
        async mounted() {
            await this.getUserInfo()
            await this.getUserOrders()
        },
        methods: {
            ...mapMutations([
                'set_orderDetailVisible',
                'set_orderDetail'
            ]),
            ...mapActions([
                'getUserInfo',
                'getUserOrders',
                'updateUserInfo',
                'updateUserPassword',
                'cancelOrder',
                'addComment'
            ]),
            saveModify() {
                this.form.validateFields((err, values) => {
                    if (!err) {
                        //判断当前是更改用户信息or密码
                        if (this.modify) {
                            const data = {
                                userName: this.form.getFieldValue('userName'),
                                phoneNumber: this.form.getFieldValue('phoneNumber')
                            }
                            this.updateUserInfo(data).then(() => {
                                this.modify = false
                            })
                        } else if (this.modify_password) {
                            const data = {
                                oldPassword: this.form.getFieldValue('oldPassword'),
                                password: this.form.getFieldValue('password')
                            }
                            this.updateUserPassword(data).then(() => {
                                this.modify_password = false
                            })
                        }
                    }
                });
            },
            modifyInfo() {
                /*    setTimeout(() => {
                    this.form.setFieldsValue({
                        'userName': this.userInfo.userName,
                        'phoneNumber': this.userInfo.phoneNumber,
                    })
                }, 0)*/
                this.modify = true
            },
            modifyPassword() {
                this.modify_password = true
            },
            cancelModify() {
                this.modify = false
                this.modify_password = false
            },
            confirmCancelOrder(orderId) {
                this.cancelOrder(orderId)
            },
            showDetail(record) {
                this.set_orderDetail(record)
                this.set_orderDetailVisible(true)
            },
            beginComment(record) {
                this.dialog = true;
                this.comment = {
                    orderId: record.id,
                    hotelId: record.hotelId,
                    userId: record.userId,
                    userName: this.userInfo.userName,
                    rate: 0,
                    content: ''
                }
            },
            cancelComment() {
                this.dialog = false;
                this.rating = 0;
                this.content = '';
                this.comment = {};
            },
            handleSubmit() {
                if (this.rating === 0)
                    alert("请为本次入住打分");
                if (this.content === '')
                    alert("请填写评价");
                else {
                    this.comment.commentContent = this.content;
                    this.comment.rate = this.rating;
                    this.addComment(this.comment);
                    this.dialog = false;
                    this.rating = 0;
                    this.content = '';
                    this.comment = {};
                    location.reload();
                }
            },
            cancelCancelOrder(){},
            nextPage() {
                if (this.page + 1 <= this.numberOfPages) this.page += 1
            },
            formerPage() {
                if (this.page - 1 >= 1) this.page -= 1
            },
            updateItemsPerPage(number) {
                this.itemsPerPage = number
            },

        }
    }
</script>
<style scoped lang="less">
    .info-wrapper {
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
    .info-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>