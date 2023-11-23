import request from '@/utils/request'

// 查询学期信息列表
export function listTerm(query) {
  return request({
    url: '/ruoyi/term/list',
    method: 'get',
    params: query
  })
}

// 查询学期信息详细
export function getTerm(id) {
  return request({
    url: '/ruoyi/term/' + id,
    method: 'get'
  })
}

// 新增学期信息
export function addTerm(data) {
  return request({
    url: '/ruoyi/term',
    method: 'post',
    data: data
  })
}

// 修改学期信息
export function updateTerm(data) {
  return request({
    url: '/ruoyi/term',
    method: 'put',
    data: data
  })
}

// 删除学期信息
export function delTerm(id) {
  return request({
    url: '/ruoyi/term/' + id,
    method: 'delete'
  })
}
