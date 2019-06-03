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
        return http.get("auth/me").then(result => commit("SET_USER_DATA",result.data))
    },
    login: ({commit},{username,password}) => {
        http.post("auth/login",{username,password}).then(result => commit("SET_TOKEN",result.data))
    }
}

const getters = {
    isAdmin: (state) => state.roles.some('admin')
}

const mutations = {
    SET_TOKEN: (state,token) => {auth.setToken(token)},
    SET_USER_DATA: (state,{username,id}) => state = {...state,username, user_id:id}

}

export default {
    namespaced:true,
    actions,
    mutations,
    state
}