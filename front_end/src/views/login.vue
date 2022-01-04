<template>
    <div class="main">
        <div class="top">
            <div class="header">
                <div>
                    <span class="title">NJU酒店管理系统</span>
                </div>
            </div>
            <div class="desc">
            </div>
        </div>
        <a-form
                id="formLogin"
                class="user-layout-login"
                ref="formLogin"
                :form="form"
        >
            <a-tabs
                    :activeKey="customActiveKey"
                    :tabBarStyle="{ textAlign: 'center', borderBottom: 'unset' }"
                    @change="handleTabClick"
            >
                <a-tab-pane key="tab1" tab="账号密码登录">
                    <v-form
                            v-model="valid_login"
                            ref="v_form_login"
                    >
                        <v-text-field
                                v-model="username"
                                outlined
                                label="邮箱"
                                required
                        ></v-text-field>
                        <v-text-field
                                :type="'password'"
                                v-model="password"
                                outlined
                                :rules="needRules"
                                label="密码"
                                required
                        ></v-text-field>
                        <v-btn
                                :disabled="!valid_login"
                                width="100%"
                                color="primary"
                                @click="submit"
                                :loading="loginLoading"
                        >
                            登录
                        </v-btn>
                    </v-form>
                </a-tab-pane>
                <a-tab-pane key="tab2" tab="注册新账号">
                    <v-form
                            v-model="valid_register"
                            ref="v_form_register"
                    >
                        <v-text-field
                                v-model="registerEmail"
                                outlined
                                :rules="emailRules"
                                label="邮箱，注意这是登录的唯一凭证"
                                required
                        ></v-text-field>
                        <v-text-field
                                v-model="registerPhone"
                                outlined
                                :rules="needRules"
                                label="手机号"
                                required
                        ></v-text-field>
                        <v-text-field
                                v-model="registerUsername"
                                outlined
                                :rules="needRules"
                                label="用户名"
                                required
                        ></v-text-field>
                        <v-text-field
                                :type="'password'"
                                v-model="registerPassword"
                                outlined
                                :rules="passwordRules"
                                label="密码(6-11个字符)"
                                required
                        ></v-text-field>
                        <v-text-field
                                :type="'password'"
                                v-model="registerPasswordConfirm"
                                outlined
                                :rules="needRules.concat(passwordConfirmationRule)"
                                label="确认密码"
                                required
                        ></v-text-field>
                        <v-btn
                                :disabled="!valid_register"
                                width="100%"
                                color="primary"
                                @click="v_register"
                                :loading="registerLoading"
                        >
                            注册
                        </v-btn>
                    </v-form>
                </a-tab-pane>
            </a-tabs>
        </a-form>

    </div>
</template>

<script>
    import {message} from 'ant-design-vue'
    import {mapGetters, mapActions, mapMutations} from 'vuex'

    export default {
        name: 'login',
        components: {},
        data() {
            return {
                customActiveKey: 'tab1',
                loginLoading: false,
                registerLoading: false,
                form: this.$form.createForm(this),
                valid_login: false,
                valid_register: false,
                password: '',
                username: '',
                registerEmail: '',
                registerPhone: '',
                registerUsername: '',
                registerPassword: '',
                registerPasswordConfirm: '',
                emailRules: [
                    v => !!v || '不能为空',
                    v => /.+@.+\..+/.test(v) || '请输入有效邮箱',
                ],
                needRules: [
                    v => !!v || '不能为空'
                ],
                passwordRules: [
                    v => !!v || '不能为空',
                    v => (v || '').length >= 6 || '最少6个字符',
                    v => (v || '').length <= 11 || '最多11个字符'
                ],
            }
        },

        computed: {
            ...mapGetters([
                'token'
            ]),
            passwordConfirmationRule() {
                return () =>
                    this.registerPassword === this.registerPasswordConfirm || "两次输入密码不一致";
            },
        },
        mounted() {

        },
        watch: {
            $route: {
                handler: function (route) {
                    this.redirect = route.query && route.query.redirect
                },
                immediate: true
            },
        },
        methods: {
            ...mapActions([
                'login',
                'register'
            ]),

            // handler
            handleTabClick(key) {
                this.customActiveKey = key
            },
            //vuetify的表单操作
            reset() {
                this.$refs.v_form_login.reset()
                this.$refs.v_form_register.reset()
            },
            submit() {
                this.loginLoading = true
                const data = {
                    email: this.username,
                    password: this.password
                }
                this.login(data).then((res) => {
                    this.loginLoading = false
                })
            },
            v_register() {
                this.registerLoading = true
                const data = {
                    email: this.registerEmail,
                    password: this.registerPassword,
                    phoneNumber: this.registerPhone,
                    userName: this.registerUsername,
                    credit: 100,
                    userType: 0
                }
                const loginData = {
                    email: this.registerEmail,
                    password: this.registerPassword
                }

                this.register(data).then((res) => {
                    this.customActiveKey = 'tab1'
                    this.reset()
                    this.registerLoading = false
                    if (res) {
                        message.success('注册成功!')
                        this.loginLoading = true
                        this.login(loginData).then((res) => {
                            this.loginLoading = false
                        })
                    }
                })
            }
        }
    }
</script>

<style lang="less" scoped>

    .main {
        min-width: 260px;
        width: 368px;
        margin: 100px auto;

        .top {
            text-align: center;

            .header {
                height: 44px;
                line-height: 44px;

                .badge {
                    position: absolute;
                    display: inline-block;
                    line-height: 1;
                    vertical-align: middle;
                    margin-left: -12px;
                    margin-top: -10px;
                    opacity: 0.8;
                }

                .logo {
                    height: 44px;
                    vertical-align: top;
                    margin-right: 16px;
                    border-style: none;
                }

                .title {
                    font-size: 33px;
                    color: rgba(0, 0, 0, .85);
                    font-family: Avenir, 'Helvetica Neue', Arial, Helvetica, sans-serif;
                    font-weight: 600;
                    position: relative;
                    top: 2px;
                }
            }

            .desc {
                font-size: 14px;
                color: rgba(0, 0, 0, 0.45);
                margin-top: 12px;
                margin-bottom: 40px;
            }
        }
    }

    .user-layout-login {
        label {
            font-size: 14px;
        }

        .getCaptcha {
            display: block;
            width: 100%;
            height: 40px;
        }

        .forge-password {
            font-size: 14px;
        }

        button.login-button {
            padding: 0 15px;
            font-size: 16px;
            height: 40px;
            width: 100%;
        }

        .user-login-other {
            text-align: left;
            margin-top: 24px;
            line-height: 22px;

            .item-icon {
                font-size: 24px;
                color: rgba(0, 0, 0, 0.2);
                margin-left: 16px;
                vertical-align: middle;
                cursor: pointer;
                transition: color 0.3s;

                &:hover {
                    color: #1890ff;
                }
            }

            .register {
                float: right;
            }
        }
    }
</style>
