import Vue from 'vue'
import authentication from './components/authentication/login.vue'
import register from './components/authentication/register.vue';
import games from './components/game/Games.vue';
import VueRouter from 'vue-router';
import VModal from 'vue-js-modal';

require('./bootstrap');

Vue.use(VueRouter);
Vue.use(VModal);

const routes = [
    { path: '/', component: authentication, name: 'login', meta: { guest: true }},
    { path: '/registreer', component: register, name: 'register', meta: { guest: true }},
    { path: '/home', component: games, name: 'games', meta: { requiresAuth: true } },
];

Vue.component('new-game-modal',
    require('./components/modals/Game/NewGameModal.vue').default
);

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

