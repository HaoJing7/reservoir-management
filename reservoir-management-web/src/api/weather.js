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

/**
 * 查询某月的气温数据
 */
export function queryTemperature(year, month) {
  return request({
    url: `/weather/temperature/${year}/${month}`,
    method: 'get',
  })
}

/**
 * 查询某年的月降水数据
 */
export function queryPrecipitation(year) {
  return request({
    url: `/weather/precipitation/${year}`,
    method: 'get',
  })
}
