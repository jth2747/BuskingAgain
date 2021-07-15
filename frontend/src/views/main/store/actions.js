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
  const url = 'users/create'
  let body = payload
  return $axios.post(url, body)
}

export function getUser ({state}, payload) {
  console.log('getUser', state, payload)
  const url = '/users/me'
  let body = payload
  return $axios.post(url, body)
}

