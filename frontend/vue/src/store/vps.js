import http from '../services/http'

const defaultCurrent = {
    userId:null,
    operatingSystem: null,
    cpucount: 0,
    cpurate:0.0,
    ram:0.0

}

const state = {
    all:[],
    byId:{},
    current: {...defaultCurrent}
}

const mutations = {
    SET_CURRENT: (state,id) => {state.current = state.byId[id] ? {...state.byId[id]} : {...defaultCurrent}},
    SET_VPS: (state,vps) => {
        state.all = vps;
        state.byId = vps.reduce((acc,cur) => {acc[cur.id] = cur; return acc}, {})
    },
    UPDATE_CURRENT: (state,current) => state.current = current,
    ADD_VPS: (state,vps) => {
        state.byId[vps.id] = vps
        state.all = [...state.all,vps]
    }
}

const actions = {
    fetch({commit}){
        return http.get("vps").then(res => commit("SET_VPS",res.data.data))
    },
    fetchById({commit}, id){
        if(!id) return Promise.resolve()
        return http.get(`vps/${id}`).then(res => commit("ADD_VPS",res.data.data))
    },
    fetchMy({commit}){
        return http.get("vps/me").then(res => commit("SET_VPS",res.data.data))
    },
    update(_,{id,vps}){
        return http.put(`vps/${id}`,vps)
    },
    create(_,vps){
        return http.post("vps",vps)
    },
    delete(_,id){
        return http.delete(`vps/${id}`);
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}