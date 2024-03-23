import request from "@/utils/request";

/**
 * 获取实时天气信息
 */
export function getRealTime() {
  return request({
    url: '/weather',
    method: 'get',
  })
}
