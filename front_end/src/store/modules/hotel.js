import {message} from 'ant-design-vue'
import store from '@/store'
import {
    getHotelsAPI,
    getHotelByIdAPI,
    getHotelsByManagerIdAPI,
} from '@/api/hotel'
import {
    reserveHotelAPI
} from '@/api/order'
import {
    orderMatchCouponsAPI,
} from '@/api/coupon'
//import {getUserOrdersAPI} from "../../api/order";
import {
    addCommentAPI,
    getHotelCommentsAPI
} from "../../api/comment";
import {getUserOrdersAPI,
        getUserThisHotelOrdersAPI
} from "@/api/order";
import user from "./user";

const hotel = {
    state: {
        managerId: '',
        manageHotelList: [],
        manageHotelListParams: {
            pageNo: 0,
            pageSize: 12
        },
        manageHotelListLoading: true,
        hotelList: [],
        hotelListParams: {
            pageNo: 0,
            pageSize: 12
        },
        hotelListLoading: true,
        currentHotelId: '',
        userId:'',
        currentHotelInfo: {},
        orderModalVisible: false,
        currentOrderRoom: {},
        orderMatchCouponList: [],
        myOrderedHotelList: [],
        userThisHotelOrders:[]
    },
    mutations: {
        set_myOrderedHotelList: function (state, data) {
            state.myOrderedHotelList = data
        },
        set_managerId: function (state, data) {
            /*            console.log("in set_manager ?")
                        console.log(data);*/
            state.managerId = data
        },
        set_manageHotelList: function (state, data) {
            state.manageHotelList = data
        },
        set_userThisHotelOrders: function (state, data) {
            state.userThisHotelOrders=data
        },
        set_manageHotelListLoading: function (state, data) {
            state.manageHotelListLoading = data
        },
        set_hotelList: function (state, data) {
            state.hotelList = data
        },
        set_userId:function (state, data) {
            state.userId = data
        },
        set_hotelListParams: function (state, data) {
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function (state, data) {
            state.hotelListLoading = data
        },
        set_currentHotelId: function (state, data) {
            state.currentHotelId = data
        },
        set_currentHotelInfo: function (state, data) {
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        set_orderModalVisible: function (state, data) {
            state.orderModalVisible = data
        },
        set_currentOrderRoom: function (state, data) {
            state.currentOrderRoom = {
                ...state.currentOrderRoom,
                ...data,
            }
        },
        set_orderMatchCouponList: function (state, data) {
            state.orderMatchCouponList = data
        }
    },

    actions: {
        getHotelList: async ({commit, state}) => {
            const res = await getHotelsAPI()
            if (res) {
                res.forEach(v => {
                    v.hotelStar = v.hotelStar == 'Three' ? 3 : (v.hotelStar == 'Four' ? 4 : 5)
                })
                commit('set_hotelList', res)
                commit('set_hotelListLoading', false)
            }
        },
        getHotelListByManagerId: async ({commit, state}) => {
            const res = await getHotelsByManagerIdAPI({
                hotelManagerId: state.managerId
            });
            if (res) {
                commit('set_manageHotelList', res);
                commit('set_manageHotelListLoading', false);
            }
        },
        getHotelById: async ({commit, state}) => {
            if(state.currentHotelId) {
                const res = await getHotelByIdAPI({
                    hotelId: state.currentHotelId
                })
                if (res) {
                    res.hotelStar = res.hotelStar == 'Three' ? 3 : (res.hotelStar == 'Four' ? 4 : 5)
                    commit('set_currentHotelInfo', res)
                }
            }
        },
        getUserThisHotelOrders: async ({state, commit}) => {
            const data={
                hotelId:state.currentHotelId,
                userId:state.userId

            }
            const res = await getUserThisHotelOrdersAPI(data)
            if (res) {
                commit('set_userThisHotelOrders',res)
            }
        },
        addOrder: async ({state, commit,dispatch}, data) => {
            const res = await reserveHotelAPI(data)
            if (res) {
                message.success('预定成功')
                commit('set_orderModalVisible', false)
                dispatch('getUserThisHotelOrders')
            }
        },
        getOrderMatchCoupons: async ({state, commit}, data) => {
            const res = await orderMatchCouponsAPI(data)
            if (res) {
                res.sort((a,b)=>{
                    if(a.discount!==0&&b.discount!==0)
                        return a.discount-b.discount
                    else if(a.discount===0&&b.discount!==0)
                        return 1
                    else if(a.discount!==0&&b.discount===0)
                        return -1
                    else return b.discountMoney-a.discountMoney
                })
                commit('set_orderMatchCouponList', res)
            }
        },
        getMyOrderedHotelList:async ({commit})=>{
            const res=await getUserOrdersAPI({userId:user.state.userId})
            if(res){
                let data=res.map(e=>{
                    return e.hotelId
                })
                commit('set_myOrderedHotelList',Array.from(new Set(data)))
            }
        },
        addComment:async ({commit},data) =>{
          const res = await addCommentAPI(data)
            if(res){
                message.success("评价成功")
            }
        }
    }
}

export default hotel