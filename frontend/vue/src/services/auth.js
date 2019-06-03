const store = {
    getToken: () => localStorage.getItem("token"),
    // eslint-disable-next-line
    setToken: s => {console.log(s); localStorage.setItem("token", s)},
    hasToken: () => !!localStorage.getItem("token"),
    removeToken: () => localStorage.removeItem("token")
}

export default store;