import {
    getManagerListAPI,
    addManagerAPI,
} from '@/api/admin'
import {getUserInfoAPI} from '@/api/user'
import { message } from 'ant-design-vue'

const admin = {
    state: {
        managerList: [

        ],
        addManagerModalVisible: false,
        addManagerParams: {
            email:'',
            password:''
        },
        hotelManagerInfoModalVisible: false,
        activeUserId:'',
        activeUserInfo:{},
    },
    mutations: {
        set_managerList: function(state, data) {
            state.managerList = data
        },
        set_addManagerModalVisible: function(state, data) {
            state.addManagerModalVisible = data
        },
        set_addManagerParams: function(state, data) {
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        set_hotelManagerInfoModalVisible:function(state, data){
            state.hotelManagerInfoModalVisible = data
        },
        set_activeUserId: (state, data) => {
            state.activeUserId = data
        },
        set_activeUserInfo: (state, data) => {
            state.activeUserInfo = {
                ...state.activeUserInfo,
                ...data
            }
        },
    },
    actions: {
        getManagerList: async({ commit }) => {
            const res = await getManagerListAPI()
            if(res){
                commit('set_managerList', res)
            }
        },
        addManager: async({ state, commit, dispatch }) => {
            const res = await addManagerAPI(state.addManagerParams)
            if(res) {
                commit('set_addManagerParams',{
                    email:'',
                    password:''
                })
                commit('set_addManagerModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
            }else{
                message.error('添加失败')
            }
        },
        get_activeUserInfo({state, commit}) {
            return new Promise((resolve, reject) => {
                getUserInfoAPI(state.activeUserId).then(response => {
                    const data = response;
                    console.log("get_activeUserInfo");
                    console.log(data);
                    commit('set_activeUserInfo', data);
                    commit('set_activeUserId', data.id);
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
    }
}
export default admin