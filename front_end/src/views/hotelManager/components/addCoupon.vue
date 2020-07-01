<template>
    <a-modal
            :visible="addCouponVisible"
            title="添加优惠策略"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="优惠券类型" v-bind="formItemLayout">
                <a-select
                        v-decorator="[
                    'couponType',
                    { rules: [{ required: true, message: '请选择房型' }] }]"
                >
                    <a-select-option value="2">多间优惠</a-select-option>
                    <a-select-option value="3">满减优惠</a-select-option>
                    <a-select-option value="4">限时优惠</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="券名" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写券名"
                        v-decorator="['couponName', { rules: [{ required: true, message: '请填写券名' }] }]"
                />
            </a-form-item>
            <a-form-item label="优惠简介" v-bind="formItemLayout">
                <a-input v-model="form.desc" type="textarea"
                         placeholder="请填写优惠简介"
                         v-decorator="['description', { rules: [{ required: true, message: '请填写优惠简介' }] }]"
                         :autoSize="{ minRows: 3, maxRows: 5 }"
                />
            </a-form-item>
            <a-form-item label="达标金额" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写达标金额"
                        v-decorator="['target_money',{ rules: [{ required: true}] ,initialValue: '0'}]"
                />
            </a-form-item>
            <a-form-item label="达标房间数" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写达标房间数"
                        v-decorator="['target_roomNum',{ rules: [{ required: true}] ,initialValue: '1'}]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="有效期">
                <a-range-picker
                        format="YYYY-MM-DD"
                        :disabled-date="disabledDate"
                        v-decorator="[
                        'date',
                        {
                            rules: [{ required: true, message: '请选择有效期' }]
                        }
                    ]"
                        :placeholder="['开始日期','结束日期']"
                />
            </a-form-item>
            <span>
                <span>
                    下面两项请选一项设置
                </span>
            </span>
            <a-form-item label="优惠金额" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写优惠金额"
                        v-decorator="['discount_money', { rules: [{ required: false}],initialValue: '0' }]"
                />
            </a-form-item>
            <a-form-item label="折扣" v-bind="formItemLayout" >
                <a-input
                        placeholder="请填写折扣"
                        v-decorator="['discount', { rules: [{ required: false}],initialValue: '0' }]"
                />
            </a-form-item>
        </a-form>

        <!-- 这里是添加策略模态框区域，请编写表单 -->
    </a-modal>
</template>
<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'

    const moment = require('moment')
    export default {
        name: 'addCouponModal',
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
            }
        },
        watch:{
            couponType(val,oldval){
                console.log(val)
                console.log(oldval)
            }
        },
        computed: {
            ...mapGetters([
                'addCouponVisible',
                'activeHotelId',
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, {name: 'addCouponModal'});
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addCouponVisible'
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                'addHotelCoupon'
            ]),
            disabledDate(current) {
                return current && current < moment().startOf('day');
            },
            cancel() {
                this.set_addCouponVisible(false)
            },
            changeType(v) {
                if (v == '3') {

                } else {
                    this.$message.warning('请实现')
                }
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            // 这里添加接口参数
                            id: null,
                            description: this.form.getFieldValue('description'),
                            name: this.form.getFieldValue('couponName'),
                            targetMoney:Number(this.form.getFieldValue('target_money')),
                            target_roomNum: Number(this.form.getFieldValue('target_roomNum')),
                            discountMoney:Number(this.form.getFieldValue('discount_money')),
                            discount:Number(this.form.getFieldValue('discount')),
                            startTime:this.form.getFieldValue('date')===null?null:moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD'),
                            endTime:this.form.getFieldValue('date')===null?null:moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD'),
                            hotelId:this.activeHotelId,
                            type: Number(this.form.getFieldValue('couponType')),
                            status: 1,
                        }
                        this.addHotelCoupon(data)
                        this.form.setFieldsValue({
                            'couponType': '',
                            'couponName': '',
                            'description': '',
                            'target_money': '0',
                            'date': '',
                            'discount_money': '',
                            'target_roomNum':'1',
                            'discount':'0',
                        })
                    }
                });
            },
        }
    }
</script>