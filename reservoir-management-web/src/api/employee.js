import request from "@/utils/request";

/**
 * 查询员工列表
 */
export function getEmployeeList(params) {
  return request({
    url: '/employee/page',
    method: 'get',
    params: params
  })
}

/**
 * 发送消息
 */
export function sendMessage(data) {
  return request({
    url: '/employee/message',
    method: 'post',
    data
  })
}

/**
 * 删除员工
 */
export function deleteEmployee(id) {
  return request({
    url: `/employee/delete/${id}`,
    method: 'delete',
  })
}

