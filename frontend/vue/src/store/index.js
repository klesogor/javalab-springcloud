import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import auth from "./auth"
import profile from "./profile"
import users from "./users"
import vps from "./vps"

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    is_loaded:true
  },
  mutations: {
    SET_LOADING: (state, payload) => state.is_loaded = payload
  },
  modules:{
    auth,
    profile,
    users,
    vps
  },
  plugins: [createPersistedState()]
})
