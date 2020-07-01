import {axios} from '@/utils/request'
import number from "less/lib/less/functions/number";

const api = {
    couponPre: '/api/coupon'
}
const couponBuff = {
    couponAft: ['/hotelMultiRoom','/hotelTargetMoney',  '/hotelTime']
}

export function hotelTargetMoneyAPI(data) {
    console.log(data)
    return axios({
        url: `${api.couponPre}${couponBuff.couponAft[data["type"]-2]}`,
        method: 'POST',
        data,
    })
}

export function hotelAllCouponsAPI(hotelId) {
    return axios({
        url: `${api.couponPre}/hotelAllCoupons`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}
export function hotelOrderedCouponsAPI(hotelId) {
    return axios({
        url: `${api.couponPre}/hotelOrderedCoupons`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}
export function orderMatchCouponsAPI(params) {
    return axios({
        url: `${api.couponPre}/orderMatchCoupons`,
        method: 'GET',
        params,
    })
}
export function annulCouponAPI(couponId) {
    return axios({
        url: `${api.couponPre}/${couponId}/annulCoupon`,
        method: 'GET',
        //data,
    })
}