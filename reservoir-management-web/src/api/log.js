import request from "@/utils/request";

/**
 * 获取日志列表
 */
export function getLogList() {
  return request({
    url: '/log/list',
    method: 'get',
  });
}
