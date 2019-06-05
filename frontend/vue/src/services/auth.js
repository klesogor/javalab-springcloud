const store = {
    getToken: () => localStorage.getItem("token"),
    setToken: s => localStorage.setItem("token", s),
    hasToken: () => !!localStorage.getItem("token"),
    removeToken: () => localStorage.removeItem("token")
}

export default store;