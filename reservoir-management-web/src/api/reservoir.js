import request from "@/utils/request";

/**
 * 获取水库列表
 */
export function getReservoirList(params) {
  return request({
    url: '/reservoir/page',
    method: 'get',
    params: params
  })
}

/**
 * 删除水库
 */
export function deleteReservoir(id) {
  return request({
    url: `/reservoir/${id}`,
    method: 'delete'
  })
}

/**
 * 根据id查询水库详情
 */
export function getReservoirDetail(id) {
  return request({
    url: `/reservoir/${id}`,
    method: 'get'
  })
}

/**
 * 编辑水库信息
 */
export function updateReservoir(data) {
  return request({
    url: '/reservoir',
    method: 'put',
    data
  })
}

/**
 * 添加水库
 */
export function addReservoir(data) {
  return request({
    url: '/reservoir',
    method: 'post',
    data
  })
}

/**
 * 获取水文数据
 */
export function getHydrology() {
  return request({
    url: '/reservoir/hydrology',
    method: 'get',
  })
}

/**
 * 修改水文数据
 */
export function updateHydrology(data) {
  return request({
    url: '/reservoir/hydrology',
    method: 'put',
    data
  })
}
