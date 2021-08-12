import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home/home'
import ConferencesDetail from '@/views/conferences/conference-detail'
import Prefer from '@/views/prefer/prefer'
import Rank from '@/views/rank/rank'

const fullMenu = require('@/views/main/menu.json')
function makeRoutesFromMenu () {
  let routes = Object.keys(fullMenu).map((key) => {
    if (key === 'home') {
      return { path: fullMenu[key].path, name: key, component: Home  }
    } else if (key === 'prefer') {
      return { path: fullMenu[key].path, name: key, component: Prefer }
    } else if (key === 'rank'){
      return { path: fullMenu[key].path, name: key, component: Rank }
    } else { // menu.json 에 들어있는 로그아웃 메뉴
      return null
    }
  })
  // 로그아웃 파싱한 부분 제거
  routes = routes.filter(item => item)
  // menu 자체에는 나오지 않는 페이지 라우터에 추가(방 상세보기)
  routes.push({
    path: '/conferences/:conferenceId',
    name: 'conference-detail',
    component: ConferencesDetail
  })
  return routes
}

const routes = makeRoutesFromMenu()

const router = createRouter({
  prefer: createWebHistory(),
  rank: createWebHistory(),
  history: createWebHistory(),
  routes
})

router.afterEach((to) => {
  console.log(to)
})

export default router
