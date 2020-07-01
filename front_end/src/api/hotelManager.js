import {axios} from '@/utils/request'

const api = {
    hotelPre: '/api/hotel'
}

export function addRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/roomInfo`,
        method: 'POST',
        data,
    })
}

export function addHotelAPI(data) {
    return axios({
        url: `${api.hotelPre}/addHotel`,
        method: 'POST',
        data,
    })

}

export function updateHotelInfoAPI(data) {
    return axios({
        url: `${api.hotelPre}/updateHotelInfo`,
        method: 'POST',
        data,
    })

}

export function editRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/editRoom`,
        method: 'POST',
        data,
    })
}

export function deleteRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/deleteRoom`,
        method: 'GET',
        params: {roomId: data}
    })
}

export function deleteHotelAPI(data) {
    return axios({
        url: `${api.hotelPre}/deleteHotel`,
        method: 'GET',
        params: {hotelId: data}
    })
}

