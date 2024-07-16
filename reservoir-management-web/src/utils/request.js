import axios from 'axios'
import store from '@/store'
import { Message } from 'element-ui'
import router from '@/router'
import message from "element-ui/packages/message";
// 创建一个新的axios实例
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // 基础地址
  timeout: 100000
})
// 成功20000 失败20001
// 请求拦截器
service.interceptors.request.use((config) => {
  // 注入token
  // this.$store.getters
  // store.getters.token => 注入请求头里面
  if (store.getters.token) {
    config.headers.authorization = `${store.getters.token}`
  }
  return config
}, (error) => {
  // 失败执行promise
  return Promise.reject(error)
})

// 响应拦截器
service.interceptors.response.use((response) => {
  // axios默认包裹了data
  const { code, msg, data } = response.data
  if (code === 20000) {
    return data
  } else {
    Message({ type: 'error', message: msg })
    return Promise.reject(new Error(msg))
  }
}, async(error) => {
  // 401 -> Unauthorized 当前请求需要用户验证
  if (error.response.status === 401) {
    // Message({ type: 'warning', message: 'token超时了' })
    Message({ type: 'warning', message: '请重新登陆！' })
    // 说明token超时了
    await store.dispatch('user/logout') // 调用action 退出登录
    //  主动跳到登录页
    router.push('/login') // 跳转到登录页
    return Promise.reject(error)
  }
  // error.message 其它的错误  ->  统一通知为 操作有误，请重试！
  Message({ type: 'warning', message: '操作有误，请重试！' })
  return Promise.reject(error)
})
// 导出service
export default service
