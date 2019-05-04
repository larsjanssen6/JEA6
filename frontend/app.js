import Vue from 'vue'
import authentication from './components/authentication/login.vue'
import register from './components/authentication/register.vue';
import games from './components/game/Games.vue';
import VueRouter from 'vue-router';
import VModal from 'vue-js-modal';
import VueSweetalert2 from 'vue-sweetalert2';
import Notification from 'vue-notification';

require('./bootstrap');

Vue.use(VueRouter);
Vue.use(VModal);
Vue.use(VueSweetalert2);
Vue.use(Notification);

window.Bus = new Vue();

const routes = [
    { path: '/', component: authentication, name: 'login', meta: { guest: true }},
    { path: '/registreer', component: register, name: 'register', meta: { guest: true }},
    { path: '/home', component: games, name: 'games', meta: { requiresAuth: true } },
];

Vue.component('socket-setup',
    require('./components/Socket/SocketSetup.vue').default
);

Vue.component('edit-profile-modal',
    require('./components/modals/Profile/EditProfileModal.vue').default
);

Vue.component('profile-modal',
    require('./components/modals/Profile/ProfileModal.vue').default
);

Vue.component('makers-modal',
    require('./components/modals/Makers/MakersModal.vue').default
);

Vue.component('logout',
    require('./components/Authentication/logout.vue').default
);

Vue.component('new-game-modal',
    require('./components/modals/Game/NewGameModal.vue').default
);

Vue.component('game-overview-modal',
    require('./components/modals/Game/GameOverviewModal.vue').default
);

Vue.component('new-user-game-modal',
    require('./components/modals/Game/NewUserGameModal.vue').default
);

Vue.component('game-modal',
    require('./components/modals/Game/GameModal.vue').default
);

Vue.component('question-modal',
    require('./components/modals/Question/QuestionModal.vue').default
);

Vue.component('new-question-modal',
    require('./components/modals/Question/NewQuestionModal.vue').default
);

Vue.component('close-modal',
    require('./components/modals/Shared/CloseModal.vue').default
);

Vue.component('score-modal',
    require('./components/modals/Score/ScoreModal.vue').default
);

const router = new VueRouter({
    routes
});

router.beforeEach((to, from, next) => {
    let token = localStorage.getItem('token');
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!token || token === null) {
            window.location.href = '/1/login';
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

