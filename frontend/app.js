import Vue from 'vue'
import test from './components/test.vue'


require('./bootstrap');



const app = new Vue({ el: '#app', components: { test }})

