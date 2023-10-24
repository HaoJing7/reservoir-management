import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/user/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/admin/user/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/admin/user/logout',
    method: 'post'
  })
}

export function addUser(data) {
  return request({
    url: '/admin/user',
    method: 'post',
    data
  })
}

/**
 * 删除用户，RESTful风格的请求
 * @param id
 * @returns {*}
 */
export function deleteUser(id) {
  return request({
    url: `/admin/user/${id}`,
    method: 'delete',
  })
}

export function updateUser(data) {
  return request({
    url: '/admin/user',
    method: 'put',
    data
  })
}
