import http from "../services/http"

const initialState = {
    age: null,
    company: null,
    email: null,
    phone: null,
    city: null
}

const state = {...initialState}

const actions = {
    fetch: ({commit}) => {
        return http.get("me/profile").then((res) => commit("SET_PROFILE_DATA",res.data.data))
    },
    update: (_,{age,city,company,email,phone}) => {
        return http.put("me/profile",{age,city,company,email,phone})
    },
    delete: ({commit}) => {
        return http.delete("me/profile").then(_ => commit("auth/INVALIDATE_LOGIN",{},{root:true}))
    }
}
    
const mutations = {
    SET_PROFILE_DATA: (store,{age,city,company,email,phone}) => {
        store.age = age
        store.city = city
        store.company = company
        store.email = email
        store.phone = phone

        return state
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}