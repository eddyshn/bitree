import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home'
import Test1 from '../views/Test1'
import Test2 from '../views/Test2'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'login',
    hidden:true,
    component: Login
  },
  {
    path: '/home',
    name: 'home',
    component: Home,
    children: [
      {
        path: '/test1',
        name: 'test1',
        component: Test1
      },
      {
        path: '/test2',
        name: 'test2',
        component: Test2
      }
    ]
    //component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
