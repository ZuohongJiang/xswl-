<template>
    <a-modal
            :visible="orderModalVisible"
            title="预定详情"
            cancelText="取消"
            okText="下单"
            @cancel="cancelOrder"
            @ok="confirmOrder"
    >
        <a-form :form="form">
            <a-form-item v-bind="formItemLayout" label="房型信息">
                <span>{{ currentOrderRoom.roomType }}</span>
            </a-form-item>
            <!-- <a-form-item v-bind="formItemLayout" label="入住人姓名">
                <a-input
                        v-decorator="[
                        'clientName',
                        { rules: [{required: true, message: '请填写入住人姓名', }] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="手机号">
                <a-input
                        v-decorator="[
                        'phoneNumber',
                        { rules: [{required: true, message: '请填写入住人联系手机', }] }
                    ]"
                />
            </a-form-item> -->

            <a-form-item v-bind="formItemLayout" label="入住日期">
                <a-range-picker
                        format="YYYY-MM-DD"
                        :disabled-date="disabledDate"
                        @change="changeDate"
                        v-decorator="[
                        'date', 
                        {
                            rules: [{ required: true, message: '请选择入住时间' }]   
                        }
                    ]"
                        :placeholder="['入住日期','退房日期']"
                />
                <div v-if="nights !== 0">共<span>{{ nights }}</span>晚</div>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="入住人数">
                <a-select
                        v-decorator="[
                        'peopleNum',
                        { rules: [{ required: true, message: '请选择入住人数' }] },
                    ]"
                        placeholder="请选择入住人数"
                        @change="changePeopleNum"
                >
                    <a-select-option :value="1">
                        1
                    </a-select-option>
                    <a-select-option :value="2">
                        2
                    </a-select-option>
                    <a-select-option :value="3">
                        3
                    </a-select-option>
                    <a-select-option :value="4">
                        4
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="有无儿童">
                <a-radio-group
                        v-decorator="[
                        'haveChild',
                        { rules: [{required: true, message: '请选择有无儿童入住', }] }
                    ]"
                >
                    <a-radio :value="1">有</a-radio>
                    <a-radio :value="0">无</a-radio>
                </a-radio-group>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="房间数">
                <a-select
                        v-decorator="[
                        'roomNum',
                        { rules: [{ required: true, message: '请选择房间数' }] },
                    ]"
                        placeholder="请选择房间数"
                        @change="changeRoomNum"
                >
                    <a-select-option :value="1">
                        1
                    </a-select-option>
                    <a-select-option :value="2">
                        2
                    </a-select-option>
                    <a-select-option :value="3">
                        3
                    </a-select-option>
                    <a-select-option :value="4">
                        4
                    </a-select-option>
                    <a-select-option :value="5">
                        5
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="房间单价">
                <span>{{ currentOrderRoom.price }}</span>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="总价">
                <span>￥{{ totalPrice }}</span>
            </a-form-item>
            <a-divider></a-divider>
            <h2 v-if="orderMatchCouponList.length>0">优惠</h2>
            <!-- <a-radio-group v-model="checkedList" @change="onchange"> -->
                <a-table
                        :columns="columns"
                        :dataSource="getAddKeyList"
                        :showHeader="true"
                        bordered
                        v-if="orderMatchCouponList.length>0"
                >

                    <span slot="couponType" slot-scope="text">
                        <a-tag color="blue">
                            <span v-if="text == '2'">多间优惠</span>
                            <span v-if="text == '3'">满减优惠</span>
                            <span v-if="text == '4'">限时优惠</span>
                        </a-tag>
                    </span>
                    <span slot="discount" slot-scope="val">
                        <span v-if="val===0">无</span>
                        <span v-else>{{val}}</span>
                    </span>
                        <span slot="discountMoney" slot-scope="val, record">
                            <span v-if="val===0">{{Math.floor(totalPrice*(1 - record.discount) * 100) / 100}}</span>
                            <span v-else>{{val}}</span>
                        </span>
                </a-table>
            <a-form-item v-bind="formItemLayout" label="结算后总价" >
                <span>￥{{ finalPrice }}</span>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<style>
</style>

<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'

    const moment = require('moment')
    const columns = [
        // {
        //     title: '勾选',
        //     dataIndex: 'id',
        //     scopedSlots: {customRender: 'id'}
        // },
        {
            title: '优惠类型',
            dataIndex: 'couponType',
            scopedSlots: {customRender: 'couponType'}
        },
        {
            title: '优惠简介',
            dataIndex: 'description',

        },
        {
            title: '折扣',
            dataIndex: 'discount',
            scopedSlots: {customRender: 'discount'}
        },
        {
            title: '优惠金额',
            dataIndex: 'discountMoney',
            scopedSlots: {customRender: 'discountMoney'}
        },
    ];
    export default {
        name: 'orderModal',
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: {span: 12},
                        sm: {span: 6},
                    },
                    wrapperCol: {
                        xs: {span: 24},
                        sm: {span: 16},
                    },
                },
                totalPrice: '',
                columns,
                checkedList: undefined,
                finalPrice: '',
                selectedItems: '0',
                nights: 0,
            }
        },
        computed: {
            ...mapGetters([
                'orderModalVisible',
                'currentOrderRoom',
                'currentHotelId',
                'currentHotelInfo',
                'userId',
                'orderMatchCouponList'
            ]),
            getAddKeyList() {
                return this.orderMatchCouponList.slice(0).map(val => val={...val, key:val.id})
            }
        },
        
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'orderModal'});
        },
        methods: {
            ...mapMutations([
                'set_orderModalVisible'
            ]),
            ...mapActions([
                'addOrder',
                'getOrderMatchCoupons'
            ]),

            disabledDate(current) {
                return current && current < moment().startOf('day');
            },
            cancelOrder() {
                this.set_orderModalVisible(false)
            },
            confirmOrder(e) {

                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            hotelId: this.currentHotelId,
                            hotelName: this.currentHotelInfo.name,
                            userId: Number(this.userId),
                            checkInDate: moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD'),
                            checkOutDate: moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD'),
                            roomType: this.currentOrderRoom.roomType,
                            roomId: this.currentOrderRoom.id,
                            roomNum: this.form.getFieldValue('roomNum'),
                            peopleNum: this.form.getFieldValue('peopleNum'),
                            haveChild: this.form.getFieldValue('haveChild'),
                            createDate: '',
                            price: this.finalPrice,
                            totalPrice: this.totalPrice
                        }
                        this.$router.push({name:"orderConfirm", params:{data: data}})
                    }
                });
             
            },
            changeDate(v) {
                if (this.totalPrice != '') {
                    this.totalPrice = this.form.getFieldValue('roomNum') * moment(v[1]).diff(moment(v[0]), 'day') * Number(this.currentOrderRoom.price)
                }
                this.nights = moment(v[1]).diff(moment(v[0]), 'day')
                this.updateFinalPrice()
            },
            changePeopleNum(v) {

            },
            async changeRoomNum(v) {
                this.totalPrice = Number(v) * Number(this.currentOrderRoom.price) * moment(this.form.getFieldValue('date')[1]).diff(moment(this.form.getFieldValue('date')[0]), 'day')
                await this.totalPrice2(v)
                this.updateFinalPrice()
            },
            async totalPrice2(val) {
                let data = {
                    userId: this.userId,
                    hotelId: this.currentHotelId,
                    orderPrice: this.totalPrice,
                    roomNum: val,
                    checkIn: moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD'),
                    checkOut: moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD'),
                }

                //总价变动可能会导致可用优惠列表变化
                await this.getOrderMatchCoupons(data)
            },
            updateFinalPrice() {
                // while (!this.flag) {}
                this.finalPrice = this.totalPrice
                this.finalPrice --;
                this.finalPrice ++;
                var i;

                for(i = 0; i < this.orderMatchCouponList.length; i ++){
                    if (this.orderMatchCouponList[i].discount != 0) {
                        this.finalPrice *= this.orderMatchCouponList[i].discount;
                    }
                    if (this.orderMatchCouponList[i].discountMoney != 0) {
                        this.finalPrice -= this.orderMatchCouponList[i].discountMoney;
                    }
                }
                if (this.finalPrice < 0)
                    this.finalPrice = 0
                this.finalPrice = Math.floor(this.finalPrice * 100) / 100
                return this.finalPrice
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            hotelId: this.currentHotelId,
                            hotelName: this.currentHotelInfo.name,
                            userId: Number(this.userId),
                            checkInDate: moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD'),
                            checkOutDate: moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD'),
                            roomType: this.currentOrderRoom.roomType,
                            roomId: this.currentOrderRoom.id,
                            roomNum: this.form.getFieldValue('roomNum'),
                            peopleNum: this.form.getFieldValue('peopleNum'),
                            haveChild: this.form.getFieldValue('haveChild'),
                            createDate: '',
                            price: this.finalPrice
                        }
                        this.addOrder(data)
                    }
                });
            },
        },
        watch: {
            
            //防止退出预订窗口重新进入另一个房间的预订窗口后，总价不更新
            orderModalVisible(val) {
                if (val)
                    if (this.totalPrice)
                        this.totalPrice = Number(this.form.getFieldValue('roomNum')) * Number(this.currentOrderRoom.price) * moment(this.form.getFieldValue('date')[1]).diff(moment(this.form.getFieldValue('date')[0]), 'day')
            }
        }
    }
</script>

