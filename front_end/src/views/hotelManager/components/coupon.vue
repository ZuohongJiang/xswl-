<template>
    <div>
        <a-modal
                :visible="couponVisible"
                title="优惠策略"
                width="1100px"
                :footer="null"
                @cancel="cancel"
        >
            <div style="width: 100%; text-align: right; margin:3px 0;">
                <a-button type="primary" size='small' @click="addCoupon()">
                    <a-icon type="plus"/>
                    添加策略
                </a-button>
                <a-divider type="vertical"></a-divider>
                <a-button type="primary" size='small' @click="order()">
                    根据优惠类型排序
                </a-button>
            </div>
            <div class="list" style="margin-top: 1%">
                <a-table
                        rowKey="id"
                        :columns="columns"
                        :dataSource="couponList"
                        bordered

                >
                <span slot="couponType" slot-scope="text">
                    <a-tag color="blue">
                    <span v-if="text == '2'">多间优惠</span>
                    <span v-if="text == '3'">满减优惠</span>
                    <span v-if="text == '4'">限时优惠</span>
                    </a-tag>
                </span>
                    <span slot="target_roomNum" slot-scope="val" >
                        <span v-if="val">{{val}}</span>
                        <span v-else>无</span>
                    </span>
                    <span slot="discount" slot-scope="val" >
                        <span v-if="val===0">无</span>
                        <span v-else>{{val}}</span>
                    </span>
                    <span slot="discountMoney" slot-scope="val" v-if="val!==0">
                        <span v-if="val===0">无</span>
                        <span v-else>{{val}}</span>
                    </span>
                    <span slot="status" slot-scope="text">
                    <a-tag color="green" v-if="text=='1'">可用</a-tag>
                    <a-tag color="red" v-if="text=='0'">不可用</a-tag>
                </span>
                    <span slot="start_time" slot-scope="text">
                    <span v-if="text===null">null</span>
                    <span v-if="text!==null">{{text}}</span>
                </span>
                    <span slot="end_time" slot-scope="text">
                    <span v-if="text === null">null</span>
                    <span v-if="text!==null">{{text}}</span>
                </span>
                    <span slot="action" slot-scope="text">

                        <a-popconfirm
                                title="你确定撤销该优惠策略吗？"
                                @confirm="confirmCancelCoupon(text)"
                                @cancel="cancelCancelCoupon"
                                okText="确定"
                                cancelText="取消"

                        >
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>

                    </span>
                </a-table>
            </div>
        </a-modal>
        <AddCoupon></AddCoupon>
    </div>
</template>
<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import AddCoupon from './addCoupon'

    const columns = [
        {
            title: '优惠编号',
            dataIndex: 'id',
        },
        {
            title: '状态',
            dataIndex: 'status',
            scopedSlots: {customRender: 'status'}
        },
        {
            title: '优惠类型',
            dataIndex: 'couponType',
            scopedSlots: {customRender: 'couponType'}
        },
        {
            title: '优惠名称',
            dataIndex: 'couponName',
        },
        {
            title: '描述',
            dataIndex: 'description',
        },
        {
            title: '最低消费额',
            dataIndex: 'targetMoney',
        },
        {
            title: '达标房间数',
            dataIndex: 'target_roomNum',
            scopedSlots: {customRender: 'target_roomNum'}
        },
        {
            title: '折扣',
            dataIndex: 'discount',
            scopedSlots: {customRender: 'discount'}
        },
        {
            title: '折扣金额',
            dataIndex: 'discountMoney',
            scopedSlots: {customRender: 'discountMoney'}
        },
        {
            title: '开始时间',
            dataIndex: 'startTime',
            scopedSlots: {customRender: 'startTime'}
        },
        {
            title: '结束时间',
            dataIndex: 'endTime',
            scopedSlots: {customRender: 'endTime'}
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        },
    ];
    export default {
        name: 'coupon',
        data() {
            return {
                columns
            }
        },
        components: {
            AddCoupon,
        },
        computed: {
            ...mapGetters([
                'couponVisible',
                'couponList',
            ])
        },
        async mounted() {
            await this.getHotelCoupon()
        },

        methods: {
            ...mapMutations([
                'set_addCouponVisible',
                'set_couponVisible',
            ]),
            ...mapActions([
                'getHotelCoupon',
                'annulCoupon',
                'showOrdered'
            ]),
            cancel() {
                this.set_couponVisible(false)
            },
            addCoupon() {
                this.set_addCouponVisible(true)
                this.set_couponVisible(false)
            },
            confirmCancelCoupon(Id) {
                this.annulCoupon(Id)
            },
            order(id) {
                this.showOrdered(id)
            },

            cancelCancelCoupon() {

            },
        },
    }
</script>
<style scoped>

</style>