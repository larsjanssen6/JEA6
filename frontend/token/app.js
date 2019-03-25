import Vue from 'vue'
import authentication from './components/authentication.vue'
import VueRouter from 'vue-router';

require('./bootstrap');

Vue.use(VueRouter);

const routes = [
    { path: '/', component: authentication, name: 'login', meta: { guest: true }},
];

const router = new VueRouter({
    routes
});

router.beforeEach((to, from, next) => {
    let token = localStorage.getItem('token');
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!token || token === null) {
            next({
                path: '/',
            });
        }
    }

    if (to.matched.some(record => record.meta.guest)) {
        if (token || token !== null) {
            next({
                path: '/home',
            });
        }
    }

    next();
});

const app = new Vue({
    router,
}).$mount('#app');

