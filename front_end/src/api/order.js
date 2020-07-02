import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order'
}
export function reserveHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}

export function getUserThisHotelOrdersAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.hotelId}/${data.userId}/getUserThisHotelOrders`,
        method: 'GET',
    })
}
export function getAllOrdersAPI() {
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
// export function getHotelOrdersAPI(hotelIdList){
//     return axios({
//         url: `${api.orderPre}/${hotelIdList}/getHotelOrders`,
//         method: 'GET',
//     })
// }
export function getHotelOrdersAPI(param){
    return axios({
        url: `${api.orderPre}/${param.hotelId}/allOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}
export function cancelOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}
export function deleteOrderAPI(orderId) {
    return axios({
        url:`${api.orderPre}/${orderId}/deleteOrder`,
        method:'GET',
    })
}
export function executeOrderAPI(orderId) {
    return axios({
        url:`${api.orderPre}/${orderId}/executeOrder`,
        method:'GET',
    })
}