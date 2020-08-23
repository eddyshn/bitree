<template>
    <div id="app">
        <div class="sidebar">
            <card></card>
            <list></list>
        </div>
        <div class="main">
            <message></message>
            <usertext></usertext>
        </div>
    </div>
</template>

<script>

    export default {
        name: 'FriendChat',
        data () {
            return {

            }
        },
        mounted:function() {
            //this.$store.dispatch('initData');
        },
        components:{

        }
    }
</script>

<style>

</style>
