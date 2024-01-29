import request from "@/utils/request";

/**
 * 获取电站列表
 */
export function getStationList(params) {
  return request({
    url: '/station/page',
    method: 'get',
    params
  })
}

/**
 * 删除电站
 */
export function deleteStation(id) {
  return request({
    url: `/station/${id}`,
    method: 'delete'
  })
}

/**
 * 根据id查询电站详情
 */
export function getStationDetail(id) {
  return request({
    url: `/station/${id}`,
    method: 'get'
  })
}

/**
 * 编辑电站信息
 */
export function updateStation(data) {
  return request({
    url: '/station',
    method: 'put',
    data
  })
}

/**
 * 添加电站
 */
export function addStation(data) {
  return request({
    url: '/station',
    method: 'post',
    data
  })
}
