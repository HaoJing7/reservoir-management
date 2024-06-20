import request from '@/utils/request'

// 获取数据
export function getData() {
  return request({
    url: '/dashboard/data',
    method: 'get',
  })
}

// 获取待办信息
export function getList() {
  return request({
    url: '/dashboard/list',
    method: 'get',
  })
}

// 根据id删除备忘录
export function deleteRecordById(id) {
  return request({
    url: `/dashboard/delete/${id}`,
    method: 'delete',
  })
}

// 添加一条备忘录
export function addRecord(data) {
  return request({
    url: '/dashboard/add',
    method: 'post',
    data
  })
}
