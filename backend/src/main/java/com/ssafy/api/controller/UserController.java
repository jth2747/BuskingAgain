package com.ssafy.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.UserFindPostReq;
import com.ssafy.api.request.UserModifyPWPatchReq;
import com.ssafy.api.request.UserModifyPutReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
	// 김은비 210723 테스트
	@Autowired
	UserService userService;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

//	private final JavaMailSender javaMailSender;
	
//	MailBodyUtil mailBodyUtil = new MailBodyUtil();
	
	@GetMapping("/create/{userid}")
	@ApiOperation(value = "아이디 중복 검사", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> checkId(@PathVariable String userid) {
		
		
		System.out.println("아이디 중복 검사");
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		boolean check = userService.checkUserId(userid);
		if(!check)
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "중복된 아이디"));
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PostMapping("/create")
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo) {
		
		
		System.out.println("회원가입");
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		User user = userService.createUser(registerInfo);
		if(user == null)
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "중복된 아이디"));
		//사용자가 있는지 없는지 판단해야함
		//지금은 200을 넣어서 무조건 success가 출력되는거
		
		
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PostMapping("/findpassword")
	@ApiOperation(value = "비밀번호 찾기", notes = "회원의 비밀번호를 이메일로 보내준다") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<String> findPassword(@RequestBody @ApiParam(value="비밀번호 찾기", required = true) UserFindPostReq findPassword) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */

		System.out.println("비밀번호 찾기");

		return null;
	}
	
	
	@PostMapping("/findid")
	@ApiOperation(value = "아이디 찾기", notes = "회원의 아이디를 찾는다") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<String> findUser(@RequestBody @ApiParam(value="아이디 찾기", required = true) UserFindPostReq findUser) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		
		
		System.out.println("아이디 찾기");
		User user = userService.findUserId(findUser.getName(), findUser.getEmail());

		String userId = null;
		
		if(user!=null) {
			System.out.println("아이디 찾았다");
			userId = user.getUserId();
			return ResponseEntity.status(200).body(userId);
		}

		System.out.println("아이디 못찾았다");
		return ResponseEntity.status(400).body(null);
	}
	
	@PostMapping("/check")
	@ApiOperation(value = "회원 본인 정보 조회 권한", notes = "로그인한 회원 본인의 정보를 권한이 있는지 확인한다.") 
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<UserLoginPostRes> checkUserInfo(@ApiIgnore Authentication authentication, @RequestBody UserModifyPWPatchReq inputpassword) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("회원정보 조회");
		System.out.println(authentication);
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		
		String password = inputpassword.getPassword();
		
		User user = userService.getUserByUserId(userId);
		System.out.println("받은 password");
		System.out.println(password);
		System.out.println("DB에 저장된 password");
		System.out.println(user.getPassword());
		System.out.println("==============================");
		System.out.println(passwordEncoder.matches(password, user.getPassword()));
		
		if(passwordEncoder.matches(password, user.getPassword())) {
			// 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
			System.out.println("비밀번호 맞음");
			return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(userId)));
		}
		// 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
		return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Password", null));

	}
	
	@GetMapping("/me")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("회원정보 조회");
		System.out.println(authentication);
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		System.out.println(user.toString());
//		System.out.println(user.getPassword());
		
		return ResponseEntity.status(200).body(UserRes.of(user));
	}

	@DeleteMapping("/{userId}")
	@ApiOperation(value = "회원 탈퇴", notes = "로그인한 회원 본인의 정보를 삭제한다.") 
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> deleteUserInfo(@ApiIgnore Authentication authentication, @PathVariable("userId") String userId) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("회원 탈퇴");
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String getUserId = userDetails.getUsername();
		if(getUserId.equals(userId)) {
			User user = userService.getUserByUserId(userId);
			Long Id = user.getId();
			userService.deleteById(Id);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		}
		return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
	}
	
	@PatchMapping("/{userId}")
	public ResponseEntity<? extends BaseResponseBody> modifyUserInfo(@ApiIgnore Authentication authentication,@PathVariable("userId") String userId,
			@RequestBody @ApiParam(value="회원 정보 수정", required = true) UserModifyPutReq modifyInfo) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		
		System.out.println("회원정보수정");
		
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String getUserId = userDetails.getUsername();
		
		System.out.println(getUserId);
		System.out.println(userId);
		
		if(getUserId.equals(userId)) {
			User getUser = userService.getUserByUserId(userId);
			Long id = getUser.getId();
			User modifyUser = userService.modifyUser(modifyInfo, userId, id);
			
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		}
		
		return ResponseEntity.status(500).body(BaseResponseBody.of(500, "수정 불가"));
		
	}
	
	@PatchMapping("/patchpw/{userId}")
	public ResponseEntity<? extends BaseResponseBody> modifyPW(@ApiIgnore Authentication authentication,@PathVariable("userId") String userId,
			@RequestBody UserModifyPWPatchReq modifyPW) {
		
		System.out.println("비밀번호 수정");
		
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String getUserId = userDetails.getUsername();
		
		System.out.println(getUserId);
		System.out.println(modifyPW.getPassword());
		
		if(getUserId.equals(userId)) {			
			User getUser = userService.getUserByUserId(userId);
			Long id = getUser.getId();
			
			userService.modifyPW(modifyPW.getPassword(), userId, id);

			System.out.println("비밀번호 수정 완료");
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		}
		
		return ResponseEntity.status(500).body(BaseResponseBody.of(500, "수정 불가"));
		
	}
	
	
}
