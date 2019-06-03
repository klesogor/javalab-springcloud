import store from "../store"
import authentication from "../services/auth"

export const guest = (to,from,next) => {
    if(authentication.hasToken()){
        return next({path:"/"})
    }
    return next()
};

export const auth = (to,from,next) => {
    store.commit("SET_LOADING", true)
    return store.dispatch("auth/fetch_user")
        .then(() => {
            store.commit("SET_LOADING", false)
            next({path:to})
        })
        .catch(()=> {
            store.commit("SET_LOADING", false)
            store.commit("auth/INVALIDATE_LOGIN")
            next({path:"login"})
        })
}

export default {guest,auth}