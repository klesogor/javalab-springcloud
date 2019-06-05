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
    login: (_,{username,password}) => {
        return http.post("auth/login",{username,password}).then(result => auth.setToken(result.data.data))
    },
    register: (_,{username,password,secret}) => {
        return http.post("auth/register",{username,password,secret, is_admin:false}).then(result => auth.setToken(result.data.data))
    },
    logout: ({commit}) => {
        commit('INVALIDATE_LOGIN')
        return Promise.resolve()
    }
}

const getters = {
    isAdmin: (state) => state.roles.some(r => r === ADMIN_ROLE),
    isLoggedIn: (state) => !!state.user_id
}

const mutations = {
    SET_USER_DATA: (state,{username,id,roles}) =>{
        state.username = username
        state.user_id = id
        state.roles = roles
    },
    INVALIDATE_LOGIN: (state) => {
        state.username = ""
        state.user_id = null
        state.roles = []
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