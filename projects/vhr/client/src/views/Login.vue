<template>
    <div>
        <el-form ref="loginForm" :rules="rules" :model="loginFrom" class="loginContainer">
            <h3 class="loginTitle">User Login</h3>
           <el-form-item prop="username">
            <el-input type="text" v-model="loginFrom.username" auto-complete="off" placeholder="please input username"></el-input>
        </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="loginFrom.password" auto-complete="off" placeholder="please input password"></el-input>
            </el-form-item>
            <el-checkbox  class="loginRemember">Remember me</el-checkbox>
            <el-button type="primary" style="width: 100%" @click="submitLogin()">Login</el-button>
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
        },
        methods:
            {
                submitLogin() {
                    var that =this;
                    this.$refs.loginForm.validate((valid) => {
                        if (valid) {
                            that.postKeyValueRequest('/doLogin', this.loginFrom)
                                .then(function (response) {
                                   if (response){
                                       window.sessionStorage.setItem('user', JSON.stringify(response.obj))
                                       that.$router.replace("/home");
                                   }
                                })
                                .catch(function (error) {
                                    console.log(error);
                                });
                        } else {
                            $this.$message.error('Please input the login information!');
                            return false;
                        }
                    });
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
     padding: 15px 35px 15px 35px;
     border: 1px solid #eaeaea;
     box-shadow: 0 0 25px #cacac6;
 }
    .loginTitle {
        margin: 20px auto 40px auto;
        text-align: center;
        color: #505458;
    }
    .loginRemember {
        margin: 0 0 20px 0;
    }
</style>
