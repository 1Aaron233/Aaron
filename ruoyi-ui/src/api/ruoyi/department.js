import request from '@/utils/request'

// 查询系部列表
export function listDepartment(query) {
  return request({
    url: '/ruoyi/department/list',
    method: 'get',
    params: query
  })
}

export function listDepartmentAll() {
  return request({
    url: '/ruoyi/department/listAll',
    method: 'get'
  })
}

// 查询系部详细
export function getDepartment(id) {
  return request({
    url: '/ruoyi/department/' + id,
    method: 'get'
  })
}

// 新增系部
export function addDepartment(data) {
  return request({
    url: '/ruoyi/department',
    method: 'post',
    data: data
  })
}

// 修改系部
export function updateDepartment(data) {
  return request({
    url: '/ruoyi/department',
    method: 'put',
    data: data
  })
}

// 删除系部
export function delDepartment(id) {
  return request({
    url: '/ruoyi/department/' + id,
    method: 'delete'
  })
}
