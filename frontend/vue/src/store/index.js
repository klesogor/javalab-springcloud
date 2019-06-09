import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import auth from "./auth"
import profile from "./profile"
import users from "./users"
import vps from "./vps"
import tickets from "./tickets"

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    is_loaded:true
  },
  mutations: {
    SET_LOADING: (state, payload) => state.is_loaded = !payload
  },
  modules:{
    auth,
    profile,
    users,
    vps,
    tickets
  },
  plugins: [createPersistedState()]
})
