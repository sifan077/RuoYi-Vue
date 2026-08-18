import request from '@/utils/request'

// 查询示例业务列表
export function listSample(query) {
  return request({
    url: '/biz/sample/list',
    method: 'get',
    params: query
  })
}

// 查询示例业务详细
export function getSample(sampleId) {
  return request({
    url: '/biz/sample/' + sampleId,
    method: 'get'
  })
}

// 新增示例业务
export function addSample(data) {
  return request({
    url: '/biz/sample',
    method: 'post',
    data: data
  })
}

// 修改示例业务
export function updateSample(data) {
  return request({
    url: '/biz/sample',
    method: 'put',
    data: data
  })
}

// 删除示例业务
export function delSample(sampleId) {
  return request({
    url: '/biz/sample/' + sampleId,
    method: 'delete'
  })
}
