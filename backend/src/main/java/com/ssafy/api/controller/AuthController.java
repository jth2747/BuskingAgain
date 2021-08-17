package com.ssafy.api.controller;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepositorySupport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * 인증 관련 API 요청 처리를 My Request위한 컨트롤러 정의.
 My Request*/
@Api(value = "인증 API", tags = {"Auth."})
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	//김은비 210719 commit 테스트
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginPostReq loginInfo) {
		String userId = loginInfo.getId();
		String password = loginInfo.getPassword();
		
		System.out.println("로그인 시작합니다아아아아");
		User user = userService.getUserByUserId(userId);
		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		System.out.println("받은 password");
		System.out.println(password);
		System.out.println("DB에 저장된 password");
		System.out.println(user.getPassword());
		System.out.println("==============================");
		System.out.println(passwordEncoder.matches(password, user.getPassword()));

		if(passwordEncoder.matches(password, user.getPassword())) {
			// 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
			System.out.println("로그인성공");
			return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(userId)));
		}
		// 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
		return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Password", null));
	}
	
	@PostMapping("/{phonenumber}")
  	public int smstest (@PathVariable("phonenumber") String phonenumber) {
    String api_key = "NCSO8ZXESKFOPTOP";
    String api_secret = "K0XDWIFD2DBPU0EL0IIRA6FSC1N3RXMZ";
    Message coolsms = new Message(api_key, api_secret);
    
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", phonenumber);
    params.put("from", "01040367669");

    int min = 10;
    int max = 1000;
    int random = (int) ((Math.random() * (max - min)) + min);
    System.out.println(random);

    params.put("type", "SMS");
    params.put("text", "인증번호는  "+ random +" 입니다. "); // 보낼 메세지를 입력하시오.
    params.put("app_version", "test app 1.2"); // application name and version

    try {
    	JSONObject obj = (JSONObject) coolsms.send(params);
    	System.out.println(obj.toString());
    } catch (CoolsmsException e) {
    	System.out.println(e.getMessage());
    	System.out.println(e.getCode());
    	
    	// front 로 인증번호를 return 해주고, front 에서는 인증번호를 받아서 해당 사용자에게 받은 번호와
    	// 같은지 비교 후 승인 or 거부
    }
    return random;
	}

}
