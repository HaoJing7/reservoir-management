import request from "@/utils/request";

/**
 * 查询所有的申请
 */
export function getAll(params) {
  return request({
    url: '/application/all',
    method: 'get',
    params
  })
}

/**
 * 查询待处理的请求
 */
export function getWaiting(params) {
  return request({
    url: '/application/waiting',
    method: 'get',
    params
  })
}

/**
 * 查询处理中的请求
 */
export function getProcessing(params) {
  return request({
    url: '/application/processing',
    method: 'get',
    params
  })
}

/**
 * 查询处理完成的请求
 */
export function getProcessed(params) {
  return request({
    url: '/application/processed',
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
    url: 'application/process/number',
    method: 'get'
  })
}
