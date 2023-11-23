import request from '@/utils/request'

// 查询任课信息列表
export function listDetail(query) {
  return request({
    url: '/ruoyi/detail/list',
    method: 'get',
    params: query
  })
}

// 查询任课信息详细
export function getDetail(id) {
  return request({
    url: '/ruoyi/detail/' + id,
    method: 'get'
  })
}

// 新增任课信息
export function addDetail(data) {
  return request({
    url: '/ruoyi/detail',
    method: 'post',
    data: data
  })
}

// 修改任课信息
export function updateDetail(data) {
  return request({
    url: '/ruoyi/detail',
    method: 'put',
    data: data
  })
}

// 删除任课信息
export function delDetail(id) {
  return request({
    url: '/ruoyi/detail/' + id,
    method: 'delete'
  })
}
