<template>
    <a-modal
            :visible="hotelManagerInfoModalVisible"
            title="酒店管理账户信息"
            footer=""
            @cancel="cancel"
    >
        <a-form :form="form" style="margin-top: 30px">
            <a-form-item label="用户id" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                <span v-if="!modify">{{activeUserInfo.id}}</span>
                <a-input v-else placeholder="请输入id" v-decorator="['id',{ rules: [{ required: true, message: '请输入酒店管理账户id' }] }]"></a-input>
            </a-form-item>
            <a-form-item label="用户名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                <span>{{ activeUserInfo.userName }}</span>
            </a-form-item>
            <a-form-item label="邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ activeUserInfo.email }}</span>
            </a-form-item>
            <a-form-item label="手机号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ activeUserInfo.phoneNumber}}</span>
            </a-form-item>
            <a-form-item label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{activeUserInfo.password}}</span>
            </a-form-item>
            <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                <a-button type="primary" @click="handleSubmit">
                    保存
                </a-button>
                <a-button type="default" style="margin-left: 30px" @click="cancel_setHotelManager">
                    取消
                </a-button>
            </a-form-item>
            <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else>
                <a-button type="primary" @click="start_setHotelManager">
                    更换管理账户
                </a-button>
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import AFormItem from "ant-design-vue/es/form/FormItem";
    export default {
        name: "hotelManagerInfoModal",
        data() {
            return {
                modify: false,
                formLayout: 'horizontal',
            }
        },
        components: {AFormItem},
        computed: {
            ...mapGetters([
                'hotelManagerInfoModalVisible',
                'managerList',
                'activeUserInfo',
                'activeUserId',
                'activeHotelId',
                'activeHotelInfo',
                'updateHotelInfoParams',

            ])
        },
        watch:{
          hotelManagerInfoModalVisible:function(val, oldVal){

          },
          activeUserInfo:function(val,oldVal){
          }
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'hotelManagerInfoModal' });
        },
        methods: {
            ...mapMutations([
                'set_hotelManagerInfoModalVisible',
                'set_activeUserInfo',
                'set_activeUserId',
                'set_activeHotelInfo',
                'set_activeHotelId',
                'set_updateHotelInfoParams',
            ]),
            ...mapActions([
                'updateHotelInfo'
            ]),
            //重置表单信息
            cancel() {
                this.set_activeUserId('');
                this.set_activeUserInfo({credit:'',email:'',id:'',password:'',phoneNumber:'',userName:'',userType:''});
                this.set_hotelManagerInfoModalVisible(false);
                this.modify=false;
            },
            start_setHotelManager(){
                this.modify=true;
            },
            cancel_setHotelManager(){
                this.modify=false;
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        this.activeHotelInfo.managerId =this.form.getFieldValue('id');
                        this.set_updateHotelInfoParams(this.activeHotelInfo);
                        this.updateHotelInfo();
                        this.set_hotelManagerInfoModalVisible(false);
                    }
                });
                this.modify=false;
            },
        }
    }
</script>

<style scoped>

</style>