# 그룹 비디오 컨퍼런스 서비스

## 목차

- [프로젝트 소개](#프로젝트-소개)   
- [텍스트 ... ](#프로젝트-소개)   
- [프로젝트 명세](#프로젝트-명세)
  - [배포 환경](#배포-환경)
  - [개발 환경](#개발-환경)
  - [Design Resources](#design-resources)
  - [핵심 라이브러리](#핵심-라이브러리)
<br>

## 프로젝트 소개
웹기반의 다목적 회의 서비스
<br>


## 프로젝트 진행률

70%

- INIT.SQL : 구현완료
- 유저 회원가입 : 구현완료 + 아이디중복검사
- 유저 정보 조회 : 토큰값을 받아오지 못함. 그외의 기능은 구현 (토큰값을 받아오지 못하여 확인 불가)
- 유저 정보 수정 : 토큰값을 받아오지 못함. 그외의 기능은 구현 (토큰값을 받아오지 못하여 확인 불가)
- 유저 정보 삭제(탈퇴) : 토큰값을 받아오지 못함. 그외의 기능은 구현 (토큰값을 받아오지 못하여 확인 불가)


## 프로젝트에서 어려웠던점

1. 프로젝트 환경 구성 : 프로젝트 환경 구성이 에러가 나면서 당황했었습니다. 학부에서 프로젝트를 진행할때 프로젝트 환경 구성에 항상 문제가 있었는데 이렇게 처음부터 잘 맞춰야한다는 것을 배웠습니다.
2. 스켈레톤 코드 읽기 : 스켈레톤 코드를 처음 봤을때 어려웠습니다. 하지만 계속 보다보니 이제는 어느정도 살을 붙일 수 있게 되었습니다. 개발자가 되기 위해서는 코들 빠르리게 익히고 흡수할 수 있어야한다는 것을 직접 느낄 수 있었습니다.
3. JPA익히기 : 이전 프로젝트에서는 MyBatis를 사용하여서 JPA가 안익숙해서 익히는것이 조금 어려웠습니다. 하지만 현업에서도 쓰이는 기술이기 때문에 이번 기회에 써볼 수 있어 좋은 경험이 될 것 같습니다.
4. JWT토큰 : Front에서 토큰 데이터를 받아오는데 back에서는 데이터가 안받아와져서 crud구현을 완성하지 못하였습니다. JWT토큰은 조금 더 공부할 필요가 있다고 느꼈습니다.
5. Front와 Back연결 : 이전 프로젝트에선 웹개발을 익히기 위해서 front와 back을 나눠서 하지 않아서 변수와 url주소를 제가 설정하여서 문제가 생겼을때 찾기가 쉬웠는데 이번의 경우에는 역할을 나눠서 하다보니 연결할때 문제가 생기면 찾는데 시간이 오래걸렸습니다. 프로젝트를 시작할때 변수명이나 경로에 대한 얘기를 조금 더 확실히 하고 시작할 필요가 있다는 것을 느꼈습니다.


## 프로젝트 명세


### 배포 환경
- __URL__ : 
- __배포 여부__ : O / X
- __접속 가능__ : 접속 가능 / 수정 중
- __HTTPS 적용__ : O / X
- __PORT__ : // 3rd Party에서 사용하는 포트가 있다면 기입해주세요. <- 기입 후 해당 주석 삭제
<br>

### 개발 환경
#### Front-end / Android
- __Framework__ : Vue.js / React / Android / Unity
- __지원 환경__ : Web / Mobile / Web App / Native App
- __담당자__ : 변우석, 장용원
<br>

#### Back-end
- __Framework__ : Spring boot / Node.js / Django / Flask
- __Database__ : MySQL / MariaDB
- __담당자__ : 권현지, 김은비, 정태현
<br>

#### Design
- __Framework 사용__ : O / X
  - [Vuetify](https://vuetifyjs.com/)
  - [Element Plus](https://element-plus.org/)
- __Design Tool 사용__ : Adobe XD, Photoshop, Blender, Zeplin 등 (없을 경우 빈칸)
- __담당자__ : 팀원 A, 팀원 B,
<br>

### Design Resources
__외부 템플릿 또는 에셋__ (이미지 또는 링크 첨부)
- 무료 이미지, 아이콘, 폰트 등은 제외
- [Vue Argon Design System](https://www.creative-tim.com/product/vue-argon-design-system?affiliate_id=116187) (무료)
  - __사용 기능__ : 디자인 전반 적용
- [Vue Black Dashboard Pro](https://www.creative-tim.com/product/vue-black-dashboard-pro?affiliate_id=116187) (유료)
  - __사용 기능__ : 캘린더 컴포넌트 사용
<br>

__자체 제작 산출물__ (필요시 이미지 또는 설명 첨부)
- LOGO
- CardView
- Button
- Calendar
<br>

### 핵심 라이브러리
기본 제공하는 라이브러리 외 핵심 기능 구현에 사용한 라이브러리가 있다면 작성해주세요.   
예시 ) VR/AR 라이브러리, 애니메이션 라이브러리, 텍스트/사진/동영상 지원, 편집 라이브러리 등

- __AR CORE__
  - __링크__ : https://developers.google.com/ar
  - __소개__ : 구글에서 제공하는 AR 지원 라이브러리
  - __사용 기능__ : 이미지 인식, 이미지 위 영상 표시
  - __담당자__ : 팀원 A, 

- __COLOR THIEF__
  - __링크__ : https://lokeshdhakar.com/projects/color-thief/
  - __소개__ : 이미지에서 색상을 추출해주는 라이브러리
  - __사용 기능__ : 커버 사진에서 색상 추출 -> 배경 색상 변경
  - __담당자__ : 팀원 A,

- __Animate.css__
  - __링크__ : https://animate.style/
  - __소개__ : css 애니메이션 지원 라이브러리
  - __사용 기능__ : 메인 페이지 진입 시 애니메이션 사용
  - __담당자__ : 팀원 A,


