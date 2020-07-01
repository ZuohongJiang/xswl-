import {
    addRoomAPI,
    addHotelAPI,
    updateHotelInfoAPI,
    editRoomAPI,
    deleteRoomAPI,
    deleteHotelAPI,
} from '@/api/hotelManager'
import {
    getAllOrdersAPI,
    deleteOrderAPI,
    getHotelOrdersAPI,
} from '@/api/order'
import {
    annulCouponAPI,
    hotelAllCouponsAPI,
    hotelTargetMoneyAPI,
    //hotelOrderedCouponsAPI,
} from '@/api/coupon'
import {message} from 'ant-design-vue'
import {hotelOrderedCouponsAPI} from "../../api/coupon";
//import {message} from 'ant-design-vue'
import {getHotelByIdAPI, getHotelsAPI} from "../../api/hotel";
//import {deleteCouponByIdAPI, disableCouponByIdAPI} from "../../api/coupon";
// import {deleteOrderAPI} from "../../api/order";
import editRoomModal from "../../views/hotelManager/components/editRoomModal";

const hotelManager = {
    state: {
        orderList: [],
        addHotelParams: {
            name: '',
            address: '',
            bizRegion: 'XiDan',
            hotelStar: '',
            rate: 0,
            description: '',
            phoneNum: '',
            managerId: '',
        },
        addHotelModalVisible: false,
        updateHotelInfoParams: {
            name: '',
            address: '',
            bizRegion: '',
            hotelStar: '',
            rate: 0,
            description: '',
            phoneNum: '',
            managerId: '',
            id: '',
        },
        updateHotelInfoModalVisible: false,

        addRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
            detail: null
        },
        addRoomModalVisible: false,
        editRoomParams: {
            roomType: '',
            id: '',
            price: '',
            total: 0,
            curNum: 0,
            detail: null
        },
        editRoomModalVisible: false,
        currentRoom: {},
        couponVisible: false,
        roomVisible: false,
        addCouponVisible: false,
        roomModalVisible: false,
        activeHotelId: 0,
        activeHotelInfo: {},
        couponList: [],
        roomList: [],
        roomDetail: {},
        hotelIdList: [],
    },
    mutations: {
        set_roomDetail: function (state, data) {
            state.roomDetail = data
        },
        set_roomModalVisible: function (state, data) {
            state.roomModalVisible = data
        },
        set_roomVisible: function (state, data) {
            state.roomVisible = data
        },
        set_roomList: function (state, data) {
            state.roomList = data
        },
        set_orderList: function (state, data) {
            state.orderList.push.apply(state.orderList, data)
        },
        set_addHotelModalVisible: function (state, data) {
            state.addHotelModalVisible = data
        },
        set_addHotelParams: function (state, data) {
            state.addHotelParams = {
                ...state.addHotelParams,
                ...data,
            }
        },
        set_addRoomModalVisible: function (state, data) {
            state.addRoomModalVisible = data
        },
        set_updateHotelInfoModalVisible: function (state, data) {
            state.updateHotelInfoModalVisible = data
        },

        set_updateHotelInfoParams: function (state, data) {
            state.updateHotelInfoParams = {
                ...state.updateHotelInfoParams,
                ...data,
            }
            // console.log(state.updateHotelInfoParams);
        },
        set_activeHotelInfo: function (state, data) {
            state.activeHotelInfo = {
                ...state.activeHotelInfo,
                ...data,
            }
        },
        set_addRoomParams: function (state, data) {
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_editRoomModalVisible: function (state, date) {
            state.editRoomModalVisible = date
        },
        set_editRoomParams: function (state, data) {
            state.editRoomParams = {
                ...state.editRoomParams,
                ...data,
            }
        },
        set_currentRoom: function (state, data) {
            state.currentRoom = {
                ...state.currentRoom,
                ...data,
            }
        },
        set_couponVisible: function (state, data) {
            state.couponVisible = data
        },
        set_activeHotelId: function (state, data) {
            state.activeHotelId = data
        },
        set_couponList: function (state, data) {
            state.couponList = data
        },
        set_addCouponVisible: function (state, data) {
            state.addCouponVisible = data
        },
        set_hotelIdList: function (state, data) {
            state.hotelIdList = data
        },
    },
    actions: {
        getAllOrders: async ({state, commit}) => {
            const res = await getAllOrdersAPI()
            if (res) {
                commit('set_orderList', res)
            }
        },
        getHotelOrders: async ({state, commit}) => {
            console.log(state.activeHotelId);
            const res = await getHotelOrdersAPI({
                hotelId: state.activeHotelId
            })
            // const res = await getAllOrdersAPI();
            if (res) {
                commit('set_orderList', res)
            }
        },
        addHotel: async ({state, dispatch, commit}) => {
            const res = await addHotelAPI(state.addHotelParams)
            if (res) {
                dispatch('getHotelList')
                commit('set_addHotelParams', {
                    name: '',
                    address: '',
                    bizRegion: 'XiDan',
                    hotelStar: '',
                    rate: 0,
                    description: '',
                    phoneNum: '',
                    managerId: '',
                })
                commit('set_addHotelModalVisible', false)
                message.success('添加成功')
            } else {
                message.error('添加失败')
            }
        },
        getHotelById: async ({commit, state}) => {
            const res = await getHotelByIdAPI({
                hotelId: state.activeHotelId
            })
            if (res) {
                commit('set_activeHotelInfo', res)
            }
        },
        updateHotelInfo: async ({state, dispatch, commit}) => {
            const res = await updateHotelInfoAPI(state.updateHotelInfoParams)
            if (res) {
                dispatch('getHotelList')
                commit('set_updateHotelInfoParams', {
                    name: '',
                    address: '',
                    bizRegion: '',
                    hotelStar: '',
                    rate: 0,
                    description: '',
                    phoneNum: '',
                    managerId: '',
                    id: '',
                })
                commit('set_updateHotelInfoModalVisible', false)
                message.success('修改成功')
            } else {
                message.error('修改失败')
            }
        },
        addRoom: async ({state, dispatch, commit}) => {
            const res = await addRoomAPI(state.addRoomParams)
            if (res === null) {
                commit('set_addRoomModalVisible', false)
                commit('set_addRoomParams', {
                    roomType: '',
                    hotelId: '',
                    price: '',
                    total: 0,
                    curNum: 0,
                    detail: null
                })
                message.success('添加成功')
            } else {
                message.error('添加失败')
            }
        },
        editRoom: async ({state, dispatch, commit}) => {
            const res = await editRoomAPI(state.editRoomParams)
            if (res === null) {
                commit('set_editRoomModalVisible', false)
                commit('set_editRoomParams', {
                    roomType: '',
                    id: '',
                    price: '',
                    total: 0,
                    curNum: 0,
                    detail: null
                })
                message.success('更新成功')
            } else {
                message.error('更新失败')
            }
        },
        getHotelCoupon: async ({state, commit}) => {
            const res = await hotelAllCouponsAPI(state.activeHotelId)
            if (res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
                commit('set_couponList', res)
            }
        },
        getHotelOrderedCoupon: async ({state, commit}) => {
            const res = await hotelOrderedCouponsAPI(state.activeHotelId)
            if (res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
                commit('set_couponList', res)
            }
        },
        showOrdered: async ({state, dispatch}) => {
            const res = await hotelOrderedCouponsAPI(state.activeHotelId)
            if (res) {
                dispatch('getHotelOrderedCoupon')
                message.success('排序成功')
            } else {
                message.error('排序失败')
            }
        },
        annulCoupon: async ({state, dispatch}, couponId) => {
            const res = await annulCouponAPI(couponId.id)
            if (res) {
                dispatch('getHotelCoupon')
                message.success('撤销成功')
            } else {
                message.error('撤销失败')
            }
        },

        getRoomList: async ({state, commit}) => {
            const res = await getHotelByIdAPI({
                hotelId: state.activeHotelId
            })
            if (res) {
                res.rooms.forEach(item => {
                    var obj = JSON.parse(item.detail)
                    Object.assign(item, obj)
                })
                commit('set_roomList', res.rooms)
            }
        },
        addHotelCoupon: async ({commit, dispatch}, data) => {
            const res = await hotelTargetMoneyAPI(data)
            if (res) {
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                commit('set_addCouponVisible', false)
                message.success('添加成功')
                dispatch('getHotelCoupon')
                commit('set_couponVisible', true)
            } else {
                // 添加失败后的操作
                message.error('添加失败')
            }
        },

        deleteOrderRecord: async ({commit, dispatch}, data) => {
            const res = await deleteOrderAPI(data)
            if (res) {
                message.success('删除成功')
                dispatch('getAllOrders')
            } else {
                message.error('删除失败')
            }
        },
        deleteRoom: async ({dispatch}, data) => {
            const res = await deleteRoomAPI(data)
            if (res) {
                dispatch('getRoomList')
                message.success('删除成功')
            } else {
                message.error('删除失败')
            }
        },
        delHotel: async ({dispatch}, data) => {
            const res = await deleteHotelAPI(data)
            if (res) {
                dispatch('getHotelList')
                message.success('删除成功')
            } else {
                message.error('删除失败')
            }
        }
    }
}
export default hotelManager