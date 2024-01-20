import request from '@/utils/request'

/**
 * 登陆
 */
export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

/**
 * 获取用户信息
 */
export function getUserInfo() {
  return request({
    url: '/user/info'
  })
}

/**
 * 修改密码
 */
export function updatePassword(data) {
  return request({
    url: '/user/updatePassword',
    method: 'put',
    data
  })
}
