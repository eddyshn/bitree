<template>
    <el-container>
        <el-header class="homeHeader">
            <div class="title">微人事</div>
            <el-dropdown style="cursor: pointer;" @command="handleCommand">
              <span class="el-dropdown-link">
                {{user.name}}<img :src="user.userface" style="width: 48px;height: 48px;border-radius: 24px;vertical-align: middle;margin-left: 8px">
              </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>个人中心</el-dropdown-item>
                    <el-dropdown-item>设置</el-dropdown-item>
                    <el-dropdown-item divided command="logout">注销登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-header>
        <el-container>
            <el-aside width="200px">
                <el-menu router>
                    <el-submenu index="1" v-for="(item, index) in routes" :key="index" v-if="!item.hidden">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>{{item.name}}</span>
                        </template>
                        <el-menu-item :index="child.path" v-for="(child, indexj) in item.children" :key="indexj">{{child.name}}</el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-container>
                <el-main>
                    <router-view></router-view>　
                </el-main>
                <el-footer>Footer</el-footer>
            </el-container>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "Home",
        data() {
            return {
                user: JSON.parse(window.sessionStorage.getItem('user'))
            }
        },
        methods: {
            handleCommand: function(command) {
                if (command === 'logout')
                    var vm =this;
                    this.$confirm('此操作将注销登录, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        vm.getRequest('/logout');
                        window.sessionStorage.removeItem('user');
                        vm.$router.replace('/');
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });
            },
            menuClick: function (index) {
                this.$router.push(index);
            }
        },
        computed: {
            routes() {
                return this.$store.state.routes;
            }
        }
    }
</script>

<style scoped>
    .homeHeader {
        background-color: #409eff;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .homeHeader .title {
        font-size: 30px;
        color: #ffffff;
    }
</style>
