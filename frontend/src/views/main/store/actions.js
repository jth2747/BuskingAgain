// API
import $axios from 'axios'

// 로그인
export function requestLogin ({ state }, payload) {
  const url = '/auth/login'
  let body = payload
  return $axios.post(url, body)
}

// 회원가입
export function requestSignup ({ state }, payload) {
  const url = '/users/create'
  let body = payload
  return $axios.post(url, body)
}

// 휴대폰 번호 확인
export function checkCNumber ({ state }, payload) {
  const url = `/auth/${payload.phone}`
  let body = payload
  return $axios.post(url, body)
}

//선호 장르
export function requestGenre ({ state }, payload) {
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
  const url = '/busking/list'
  return $axios.get(url)
}

// 방 랜덤 목록 조회(홈 화면에 나오는 정보)
export function roomRandomList ( { state }, payload) {
  const url = '/busking/randomlist'
  return $axios.get(url)
}

// 버스킹 목록 검색(검색한 목록 불러오기)
export function findRoomList({state}, payload) {
  const url = `/busking/search/${payload.title}`
  return $axios.get(url)
}

// 선호 장르 방 목록 조회(선호장르 화면에 나오는 정보)
export function roomGenreList ( { state }, payload) {
  const url = '/busking/genrelist'
  return $axios.get(url, {
    headers: {
      Authorization: 'Bearer ' + payload.token,
    }
  })
}

// 방 상세 정보(버스킹 방에 들어가면 나오는 정보)
export function roomDetail ( { state }, payload) {
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
  const url = `users/create/${payload.id}`
  return $axios.get(url)
}

// 회원정보 조회하기 전 비밀번호 확인
export function checkPassword ( {state}, payload) {
  const url = `/users/check`
  let body = payload
  return $axios.post(url, body,
    {
      headers: {
        Authorization: 'Bearer ' + payload.token,
    }
  })
}

// 장르 조회
export function getGenre ( { state }, payload) {
  const url = '/genre/list'
  return $axios.get(url, {
    headers: {
      Authorization: 'Bearer ' + payload.token,
    }
  })
}


// 버스킹 방 나갔을 때
export function roomOut ( { state }, payload) {
  const url = `/busking/${payload.id}`
  return $axios.delete(url, {
    headers: {
      Authorization: 'Bearer ' + payload.token,
    }
  })
}

// 아이디 찾기
export function findId ( { state }, payload) {
  const url = '/users/findid'
  let body = payload
  return $axios.post(url, body)
}

// 비밀번호 찾기
export function findPassword ( { state }, payload) {
  const url = '/users/findpassword'
  let body = payload
  return $axios.post(url, body)
}

// 좋아요 순 랭킹
export function roomRankLike ( { state }, payload) {
  const url = '/busking/ranklike'
  return $axios.get(url)
}

// 접속자 순 랭킹
export function roomRankPerson ( { state }, payload) {
  const url = '/busking/rankperson'
  return $axios.get(url)
}

// 버스킹 생성 이력
export function roomHistory ( { state }, payload) {
  const url = '/busking/history'
  return $axios.get(url, {
    headers: {
      Authorization: 'Bearer ' + payload.token,
    }
  })
}
