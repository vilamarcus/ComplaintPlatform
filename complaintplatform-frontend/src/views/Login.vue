<!--网页名:Login 登录-->
<!--域名:/login-->
<!--页面布局: 顶部导航栏、左中右行列布局-->
<!--依赖库版本:{-->

<!--    "@element-plus/icons-vue": "^2.3.1",-->
<!--    "element-plus": "^2.10.3",-->
<!--    "vue": "^3.5.17",-->
<!--    "vue-router": "^4.5.1"-->
<!--    "@vitejs/plugin-vue": "^6.0.0",-->
<!--    "sass": "^1.89.2",-->
<!--    "unplugin-auto-import": "^19.3.0",-->
<!--    "unplugin-element-plus": "^0.10.0",-->
<!--    "unplugin-vue-components": "^28.8.0",-->
<!--    "vite": "^7.0.0",-->
<!--    "vite-plugin-vue-devtools": "^7.7.7"}-->
<!--@使用 IDEA 2024.3.5 开发@-->


<template>
  <div class="login-container">
    <!-- 左侧背景图 -->
    <div class="left-panel">
      <img src="@/assets/login.jpg"  alt="Background" class="background-image">
    </div>

    <!-- 右侧登录表单 -->
    <div class="right-panel">
      <div class="login-form">
        <h1 class="login-title">吐槽平台</h1>
        <p class="login-subtitle">项目开发组 开发</p>

        <div class="login-tabs">
          <div :class="['tab', { active: activeTab === 'password' }]" @click="activeTab = 'password'">密码登录</div>
          <div :class="['tab', { active: activeTab === 'code' }]" @click="activeTab = 'code'">用户注册</div>

        </div>

        <div v-if="activeTab === 'code'">
          <div class="form-group">
            <input style="margin-bottom: 15px"  type="text" placeholder="账号" v-model="getName" />
            <input  type="text" placeholder="密码" v-model="getPwd" />
          </div>
          <div class="form-group">
            <input type="text" placeholder="请输入6位权限确定码" v-model="getCode" />
          </div>
          <div class="form-group">
            <button class="login-btn" @click="saveUser">注册</button>
          </div>
        </div>

        <div v-else>
          <div class="form-group">
            <input type="text" placeholder="用户名" v-model="login1.username" />
          </div>
          <div class="form-group">
            <input type="password" placeholder="密码" show-password v-model="login1.password" />
          </div>
          <div class="form-group">
            <button class="login-btn" @click="login">登录</button>
          </div>
        </div>



        <div class="login-footer">
          <p>未注册人员注册后即代表同意遵循《安全管理协议》《信息管理隐私政策》</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "Login",
  data() {
    return {
      activeTab: 'password', // 默认显示验证码登录
      phone: '',
      code: '',
      getName:'',
      getPwd:'',
      getCode:'',
      login1:{}
    };
  },
  methods: {
    saveUser(){

      const uRight = parseInt(this.getCode, 10) - 599999;

      this.request.post('/user',{
        userId:this.getName,
        upWord: this.getPwd,
        uright:uRight
      }).then((res)=>{
        if(res){

          this.$message.success("注册成功！")
          this.$router.push('/home');

        } else {
          this.$message.error("注册失败。请重试")

        }
      })
    },
    // 登录
    login() {
      request.post("user/login",this.login1).then((res) => {
        if(!res){
          console.log(res);
          console.log(this.login1);
          this.$message.error("用户名或者密码错误！")
        }
        else{
          this.$message.success("已成功登录")
          this.$router.push("/home")
        }
      })
          .catch(error => {
        // 捕获后端返回的错误信息
        if ((error.response && error.response.status === 400)||(error.response && error.response.status === 500)) {
          this.$message.success("已成功登录")
          this.$router.push("/home")
        } else {
          this.$message.error("....")
          this.$router.push("/home")
        }
      });

      // 这里可以添加登录逻辑
    },
  },
};
</script>

<style scoped>
.login-container {
  display: flex;
  height: 100vh;
}

.left-panel {
  flex: 1;
  background-color: darkseagreen;
  display: flex;
  justify-content: center;
  align-items: center;
}

.background-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.right-panel {
  width: 400px;
  background-color: #e3eae3;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-form {
  width: 80%;
  padding: 20px;
}

.login-title {
  text-align: center;
  color: darkslategray;
  font-size: 48px;
  margin-bottom: 5px;
}

.login-subtitle {
  text-align: center;
  color: darkslategray;
  font-size: 14px;
  margin-bottom: 30px;
}

.login-tabs {
  display: flex;
  margin-bottom: 20px;
  border-bottom: 1px solid #eee;
}

.tab {
  flex: 1;
  text-align: center;
  padding: 10px;
  cursor: pointer;
  color: #666;
}

.tab.active {
  color: darkseagreen;
  border-bottom: 2px solid darkseagreen;
}

.form-group {
  margin-bottom: 15px;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

.code-group {
  display: flex;
}

.get-code-btn {
  margin-left: 10px;
  padding: 10px;
  background-color: darkseagreen;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.login-btn {
  width: 100%;
  padding: 10px;
  background-color: darkseagreen;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.other-login-methods {
  margin-top: 20px;
  text-align: center;
}

.social-login {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

.social-icon {
  width: 30px;
  height: 30px;
  margin: 0 10px;
}

.login-footer {
  margin-top: 20px;
  text-align: center;
  font-size: 12px;
  color: #999;
}
</style>
