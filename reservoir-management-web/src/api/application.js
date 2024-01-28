import request from "@/utils/request";

/**
 * 查询所有的申请
 */
export function getApplicationList(params) {
  return request({
    url: '/application/page',
    method: 'get',
    params
  })
}

/**
 * 查询待处理的总数
 */
export function getWaitingNumber() {
  return request({
    url: '/application/waiting/number',
    method: 'get'
  })
}

/**
 * 查询处理中的总数
 */
export function getProcessingNumber() {
  return request({
    url: '/application/processing/number',
    method: 'get'
  })
}

/**
 * 更改申请状态
 */
export function changeStatus(data) {
  return request({
    url: `/application/status`,
    method: 'put',
    data
  })
}

/**
 * 获取申请详情
 */
export function getApplicationDetail(id) {
  return request({
    url: `/application/detail/${id}`,
    method: 'get',
  })
}
