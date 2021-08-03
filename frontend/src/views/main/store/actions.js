// API
import $axios from 'axios'

// 로그인
export function requestLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = '/auth/login'
  let body = payload
  return $axios.post(url, body)
}

// 회원가입
export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = '/users/create'
  let body = payload
  return $axios.post(url, body)
}

//선호 장르
export function requestGenre ({ state }, payload) {
  console.log('requestGenre', state, payload)
  console.log(state)
  console.log(payload)
  const url = '/genre'
  let body = payload
  return $axios.post(url, body,
    {
      headers: {
        Authorization: 'Bearer ' + payload.token,
    }
  })
}

// 버스킹 방 생성
export function requestBusking ({ state }, payload) {
  console.log('requestBusking', state, payload)
  const url = '/busking/create'
  let body = payload
  return $axios.post(url, body,
    {
      headers: {
        Authorization: 'Bearer ' + payload.token,
    }
  })
}

// 회원 정보 조회
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

// 회원 탈퇴
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

// 회원 정보 수정
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

// 비밀번호 변경
export function passwordChange ( { state }, payload) {
  console.log('changePassword', state, payload)
  const url = `/users/patchpw/${payload.userId}`
  let body = payload
  return $axios.patch(url, body,
    {
      headers: {
        Authorization: 'Bearer ' + payload.token,
    }
  })
}

// 방 목록 조회(홈 화면에 나오는 정보)
export function roomList ( { state }, payload) {
  console.log('roomList', state, payload)
  const url = '/busking/list'
  return $axios.get(url)
}


// 방 상세 정보(버스킹 방에 들어가면 나오는 정보)
export function roomDetail ( { state }, payload) {
  console.log('roomDetail', state, payload)
  const url = `/busking/${payload.id}`
  return $axios.get(url,
    {
      headers: {
        Authorization: 'Bearer ' + payload.token,
    }
  })
}


// 버스킹 정보 수정
export function updateRoom ( { state }, payload) {
  console.log('updateRoom', state, payload)
  const url = `/busking/${payload.id}`
  let body = payload
  return $axios.patch(url, body,
    {
      headers: {
        Authorization: 'Bearer ' + payload.token,
    }
  })
}


// 버스킹 종료
export function deleteRoom ( { state }, payload) {
  console.log('deleteRoom', state, payload)
  const url = `/busking/${payload.id}`
  return $axios.delete(url,
    {
      headers: {
        Authorization: 'Bearer ' + payload.token,
    }
  })
}

// 아이디 중복 체크
export function checkSignupId ( {state}, payload) {
  console.log('checkSignupId', state, payload)
  const url = `users/create/${payload.id}`
  return $axios.get(url)
}

// 회원정보 조회하기 전 비밀번호 확인
export function checkPassword ( {state}, payload) {
  console.log('checkPassword', state, payload)
  const url = 'user/check'
  let body = payload.password
  return $axios.get(url, body,
    {
      headers: {
        Authorization: 'Bearer ' + payload.token,
    }
  })
}
