Vue.component('todo-item', {
    props:['td'],
    template: '<li>这是个待办项组件{{td.text}}}</li>'
});

var app = new Vue(
    {
        el: '#app',
        data: {
            message: 'hello vue',
            title: 'test title',
            seen: true,
            todos:[
                { text: '学习 JavaScript' },
                { text: '学习 Vue' },
                { text: '整个牛项目' }
            ]
        },
        methods:{
            clickMe: function () {
                this.message = 'message update by click';
            }
        }
    }
);
