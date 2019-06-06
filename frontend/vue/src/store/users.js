import http from "../services/http"

const state = {
    all: [],
    byId: {}
}

const mutations = {
    SET_STATE = (state,users) => {
        state.all = users;
        state.byId = users.reduce((acc,cur) => {acc[cur.id] = cur;return acc},{})
    }
}

const actions = {
    fetch({commit}){
        return http.get("users").then(res => commit("SET_STATE",res.data.data))
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}