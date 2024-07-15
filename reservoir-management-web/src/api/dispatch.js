import request from "@/utils/request";

export function executeAlgorithm(data) {
  return request({
    url: '/dispatch/execute',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data: data
  });
}
