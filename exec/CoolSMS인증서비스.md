### 목적

회원가입 시 휴대폰 인증서비스 구현

### Cool SMS 란?

문자메시지 웹발송 지원, 2008년부터 국내 최초로 REST API 및 오픈소스 공개한 인증서비스 업체

### 활용방안

회원가입 서비스 구현 후, CoolSMS 로 요청을 보내 SMS 문자 발송

# CoolSMS 서비스 등록 방법

## 1) CoolSMS 인증서비스 사이트 회원가입

[세상에서 가장 안정적이고 빠른 메시지](https://coolsms.co.kr/)

- 가입 후 휴대폰 인증을 통한 홈페이지 내 본인 인증 필수

![Untitled.png (1480×873)](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/4dc0eba9-baa1-4625-8877-dc7d20e72bf8/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210819%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210819T074554Z&X-Amz-Expires=86400&X-Amz-Signature=0022633ccf3588b1ccb7326ef71c0de81c61209c710ebbe6243e7201126ac883&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

## 2) **API Key, API Secret 생성**

**로그인 후 , 개발/연동 - API Key 관리에서, `새 API KEY 생성` 으로 API Key, API Secret 생성**

외부노출 시 캐쉬, 포인트 등 타인이 남용할 수 있으니 API Key, API Secret 보안 유지 각별히 주의

## 3) **발신번호 등록**

환경설정 - 발신번호 관리에서 발신번호 등록하기 버튼 클릭

![Untitled.png (846×583)](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/ff2c929d-c745-4a83-9068-e900becfad90/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210819%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210819T074730Z&X-Amz-Expires=86400&X-Amz-Signature=c4f768a4b54b5986fbf5e44ec70f5c4791b397691b602a424bada3bb01b94793&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

# 전송결과 확인

**메세지전송 - 전송결과에서 요청받은 메세지 건에 대한 처리결과 조회 가능**

참고 사이트

https://developer.coolsms.co.kr/developer

https://developer.coolsms.co.kr/SMS_API_v2

