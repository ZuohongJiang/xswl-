import Vue from 'vue'
import router from '@/router'
import {getToken, setToken, removeToken} from '@/utils/auth'
import {resetRouter} from '@/router'
import {message} from 'ant-design-vue'
import {
    loginAPI,
    registerAPI,
    getUserInfoAPI,
    updateUserInfoAPI,
    getUserNameAPI
} from '@/api/user'

import {
    getUserOrdersAPI,
    cancelOrderAPI,
    getOrderDetailAPI,
    getUserThisHotelOrdersAPI,
} from '@/api/order'
import {updateUserPasswordAPI} from "../../api/user";
import {state} from "../../store/modules/hotel";

const getDefaultState = () => {
    return {
        userId: '',
        currentHotelId: '',
        userInfo: {},
        userOrderList: [],
        userThisHotelOrders: [],
        orderDetailVisible: false,
        orderDetail: {}
    }
}

const user = {
    state: getDefaultState(),

    mutations: {
        reset_state: function (state) {
            state.token = '',
                state.userId = '',
                state.currentHotelId = '',
                state.userInfo = {},
                state.userOrderList = []
        },
        set_token: function (state, token) {
            state.token = token
        },
        set_email: (state, data) => {
            state.email = data
        },
        set_userId: (state, data) => {
            state.userId = data
        },
        set_hotelId: (state, data) => {
            state.currentHotelId = data
        },
        set_userInfo: (state, data) => {
            state.userInfo = {
                ...state.userInfo,
                ...data
            }
        },
        set_userOrderList: (state, data) => {
            state.userOrderList = data
        },
        set_orderDetailVisible: (state, data) => {
            state.orderDetailVisible = data
        },
        set_orderDetail: (state, data) => {
            state.orderDetail = data
        },
        set_userThisHotelOrders: (state, data) => {
            state.userThisHotelOrders = data
        }
    },

    actions: {
        //处理登录相关的方法
        login: async ({dispatch, commit}, userData) => {
            const res = await loginAPI(userData)
            console.log(res)
            if (res) {
                setToken(res.id)
                commit('set_userId', res.id)
                commit('set_hotelId', res.currentHotelId)
                dispatch('getUserInfo')
                router.push('/hotel/hotelList')
            }
        },
        register: async ({commit}, data) => {
            const res = await registerAPI(data)
            console.log(res)
            if (res) {
                // message.success('注册成功')
                return true
            }
            return false
        },
        getUserInfo({state, commit}) {
            return new Promise((resolve, reject) => {
                getUserInfoAPI(state.userId).then(response => {
                    const data = response
                    if (!data) {
                        reject('登录已过期，请重新登录')
                    }
                    commit('set_userInfo', data)
                    commit('set_userId', data.id)
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },


        getUserName: async ({state}, data) => {
            const res = await getUserNameAPI(data);
            if (res) {
                return res;
            }
        },

        //更新用户信息
        updateUserInfo: async ({state, dispatch}, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if (res) {
                message.success('修改成功')
                dispatch('getUserInfo')
            }
        },
        updateUserPassword: async ({state, dispatch}, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserPasswordAPI(params)
            if (res) {
                message.success('修改成功')
                dispatch('getUserInfo')
            }
        },

        //用户获取、处理订单的方法
        getUserOrders: async ({state, commit}) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserOrdersAPI(data)
            if (res) {
                commit('set_userOrderList', res)
            }
        },
        cancelOrder: async ({state, dispatch}, orderId) => {
            const res = await cancelOrderAPI(orderId)
            if (res) {
                dispatch('getUserOrders')
                message.success('撤销成功')
            } else {
                message.error('撤销失败')
            }
        },
        
        getOrderDetail: async ({commit}, orderId) => {
            const res = await getOrderDetailAPI(orderId);
            commit('set_orderDetail', res);
        },

        //登出
        logout: async ({commit}) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },
        // remove token
        resetToken({commit}) {
            return new Promise(resolve => {
                removeToken() // must remove  token  first
                commit('reset_state')
                resolve()
            })
        },
    }
}

export default user