import { axios } from '@/utils/request'
const api = {
    commentPre: '/api/comment'
}
export function addCommentAPI(data){

    return axios({
        url: `${api.commentPre}/addComment`,
        method: 'POST',
        data
    })
}
export function getHotelCommentsAPI(hotelId){
    return axios({
        url:`${api.commentPre}/getHotelComments`,
        method:'GET',
        params: {hotelId: hotelId},
    })
}