// API
import $axios from 'axios'


export function requestLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = '/auth/login'
  let body = payload
  return $axios.post(url, body)
}

export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = '/users/create'
  let body = payload
  return $axios.post(url, body)
}

export function requestBusking ({ state }, payload) {
  console.log('requestBusking', state, payload)
  const url = '/buksing/create'
  let body = payload
  return $axios.post(url, body)
}

export function getUser ( { state }, payload) {
  console.log('getUser', state, payload)
  const url = '/users/me'
  // let body = payload
  return $axios.get(url, {
    headers: {
      Authorization: 'Bearer ' + payload.token,
    }
  })
}

export function deleteUser ( { state }, payload) {
  console.log('deleteUser', state, payload)
  const url = `/users/${payload.userId}`
  // let body = payload
  return $axios.delete(url, {
    headers: {
      Authorization: 'Bearer ' + payload.token,
    }
  })
}

export function updateUser ( { state }, payload) {
  console.log('updateUser', state, payload)
  const url = `/users/${payload.userId}`
  let body = payload
  return $axios.patch(url, body,
    {
      headers: {
        Authorization: 'Bearer ' + payload.token,
    }
  })
}
