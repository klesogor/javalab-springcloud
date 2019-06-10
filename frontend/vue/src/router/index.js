import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Profile from '../views/Profile.vue'
import VpsForm from "../views/vps/Form"
import TicketDetails from "../views/tickets/Details"
import Logs from "../views/Logs.vue"
import {auth,guest} from './middleware'

Vue.use(Router)

const middleware = (cb,...routes) => routes.map(r => ({...r,beforeEnter:cb}))

const guestRoutes = [
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  }
]

const authRoutes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile
  },
  {
    path: '/vps/form/:id?',
    name: "vpsForm",
    component: VpsForm
  },
  {
    path: '/ticket/:id',
    name: "ticketDetails",
    component:TicketDetails
  },
  {
    path: '/logs',
    name: 'logs',
    component: Logs
  }
]

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    ...middleware(auth,...authRoutes),
    ...middleware(guest,...guestRoutes)
  ]
})
