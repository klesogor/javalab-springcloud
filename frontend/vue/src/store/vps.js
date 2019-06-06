import http from '../services/http'

const defaultCurrent = {
    ownerId:null,
    operatingSystem: null,
    cpuCount: 0,
    cpuRate:0.0,
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
    UPDATE_CURRENT: (state,current) => state.current = current
}

const actions = {
    fetch({commit}){
        return http.get("vps").then(res => commit("SET_VPS",res.data.data))
    },
    update(_,{id,vps}){
        return http.put(`vps/${id}`,vps)
    },
    create(_,vps){
        return http.post("vps",vps)
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}