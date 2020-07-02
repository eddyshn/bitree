import Vue from 'vue';
import Vuex from 'vuex';

import './style/common.scss'

Vue.use(Vuex);

const sore = new Vuex.Store({
    state: {
        count: 0
    }
});

var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
})
