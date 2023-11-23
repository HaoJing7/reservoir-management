import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/admin/user/info',
    method: 'get',
    params: {token}
  })
}

export function logout() {
  return request({
    url: '/admin/user/logout',
    method: 'post'
  })
}

/**
 * 修改密码
 */
export function updatePwd(password) {
  return request({
    url: '/admin/user/password',
    method: 'put',
    params: {password}
  })
}

// TODO 员工列表
/**
 * 查询管理员列表
 */

// TODO 添加员工
/**
 * 添加管理员
 */
