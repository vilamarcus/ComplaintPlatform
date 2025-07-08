<template>
  <div id="app">
    <nav>
      <router-link to="/">首页</router-link> |
      <router-link to="/about">关于</router-link>
    </nav>
    <router-view/>
  </div>
</template>

<script>
import axios from 'axios'

// 创建axios实例
const instance = axios.create({
  baseURL: '/api',
  timeout: 5000
})

// 请求拦截器
instance.interceptors.request.use(config => {
  // 可以在这里统一添加token等
  // const token = localStorage.getItem('token')
  // if (token) {
  //   config.headers.Authorization = `Bearer ${token}`
  // }
  return config
}, error => {
  return Promise.reject(error)
})

// 响应拦截器
instance.interceptors.response.use(response => {
  return response.data
}, error => {
  // 统一处理错误
  if (error.response) {
    switch (error.response.status) {
      case 401:
        // 处理未授权
        break
      case 404:
        // 处理未找到
        break
      // 其他状态码处理
    }
  }
  return Promise.reject(error)
})

export default {
  name: 'App',
  beforeCreate() {
    // 将axios实例挂载到Vue原型
    Vue.prototype.$axios = instance
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 20px;
}

nav {
  padding: 20px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  margin: 0 5px;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
