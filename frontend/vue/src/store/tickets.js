import http from '../services/http'

const state = {
    all:[],
    byId:{},
}

const mutations = {
    SET_TICKETS: (state,tickets) => {
        state.all = tickets;
        state.byId = tickets.reduce((acc,cur) => {acc[cur.id] = cur; return acc}, {})
    },
    ADD_TICKET: (state,ticket) => {
        state.all = [...state.all.filter(x => x.id !== ticket.id), ticket]
        state.byId[ticket.id] = ticket;
    },
    ADD_COMMENT_TO_TICKET: (state, {ticketId, comment}) => {
        state.byId[ticketId].comments = [...state.byId[ticketId].comments, comment] 
    }
}

const actions = {
    fetch({commit}){
        return http.get("ticket").then(res => commit("SET_TICKETS",res.data.data))
    },
    fetchById({commit}, id){
        if(!id) return Promise.resolve()
        return http.get(`ticket/${id}`).then(res => commit("ADD_TICKET",res.data.data))
    },
    fetchMy({commit}){
        return http.get("ticket/me").then(res => commit("SET_TICKETS",res.data.data))
    },
    create(_,ticket){
        return http.post("ticket",ticket).then(resp => resp.data.data)
    },
    close(_,id){
        return http.delete(`ticket/${id}`);
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}