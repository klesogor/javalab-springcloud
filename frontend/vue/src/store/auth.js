import http from "../services/http"
import auth from "../services/auth"

const ADMIN_ROLE = "admin"

const state = {
    username: "",
    user_id: null,
    roles: []
}

const actions = {
    fetch_user: ({commit}) => {
        return http.get("me").then(result => commit("SET_USER_DATA",result.data.data))
    },
    login: ({commit},{username,password}) => {
        http.post("auth/login",{username,password}).then(result => auth.setToken(result.data.data))
    },
    register: ({commit},{username,password,secret}) => {
        http.post("auth/register",{username,password,secret, is_admin:false}).then(result => auth.setToken(result.data.data))
    }
}

const getters = {
    isAdmin: (state) => state.roles.some(r => r === ADMIN_ROLE),
    isLoggedIn: (state) => !!state.user_id
}

const mutations = {
    SET_USER_DATA: (state,{username,id,roles}) => state = {...state,username, user_id:id,roles}     ,
    INVALIDATE_LOGIN: (state) => {
        state = {...state, username: "",user_id:null,roles:[]}
        auth.removeToken();
        return state
    }
}

export default {
    namespaced:true,
    actions,
    mutations,
    state,
    getters
}