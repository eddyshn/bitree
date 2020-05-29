<template>
    <div>
        <el-form :rules="rules" :model="loginFrom" class="loginContainer">
           <el-form-item prop="username">
            <el-input type="text" v-model="loginFrom.username" auto-complete="off" placeholder="please input username"></el-input>
        </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="loginFrom.password" auto-complete="off" placeholder="please input password"></el-input>
            </el-form-item>
            <el-checkbox v-bind="checked"></el-checkbox>
            <el-button type="primary" style="width: 100%">Login</el-button>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                loginFrom: {
                    username: "admin",
                    password: "123"
                },
                rules : {
                    username: [{required: true, message: 'pleases input username', trigger: 'blur'}],
                    password: [{required: true, message: 'pleases input password', trigger: 'blur'}]
                },
                checked: true
            }
        }
    }
</script>

<style>
 .loginContainer {
     border-radius: 15px;
     background-clip: padding-box;
     background: #fff;
     margin: 180px auto;
     width: 350px;
     padding: 35px 35px 15px 35px;
     border: 1px solid #eaeaea;
     box-shadow: 0 0 25px #cacac6;
 }
</style>
