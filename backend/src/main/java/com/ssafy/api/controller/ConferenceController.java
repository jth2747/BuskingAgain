package com.ssafy.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.ConferenceCreatePostReq;
import com.ssafy.api.service.ConferenceService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "방 API", tags = {"Conferences"})
@RestController
@RequestMapping("api/v1/conferences")
public class ConferenceController {

	@Autowired
	ConferenceService conferenceService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/create")
	@ApiOperation(value = "방 생성", notes = "로그인한 회원의 방을 만든다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> create(@ApiIgnore Authentication authentication,
			@RequestBody @ApiParam(value="방생성 정보", required = true) ConferenceCreatePostReq conferenceCreateInfo
			){
		System.out.println("방생성하자");
		//현재는 owner_id가 없음
		//토큰에 owner_id를 받아오면
//		회원정보를 조회해서 owner_id를 받아옴
		System.out.println("Ownerid를 위해서 회원정보 조회");
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		Conference conference = conferenceService.createConference(conferenceCreateInfo, user.getId());

		
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "방생성 Success"));
	}
	
//	@GetMapping("/this")
//	@ApiOperation(value = "방 정보 조회", notes = "선택한 방의 정보를 응답한다.") 
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//        @ApiResponse(code = 401, message = "인증 실패"),
//        @ApiResponse(code = 404, message = "사용자 없음"),
//        @ApiResponse(code = 500, message = "서버 오류")
//    })
//	public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) {
//		/**
//		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
//		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
//		 */
//		System.out.println("회원정보 조회");
//		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
//		String userId = userDetails.getUsername();
//		User user = userService.getUserByUserId(userId);
//		System.out.println(user.getPassword());
//		
//		return ResponseEntity.status(200).body(UserRes.of(user));
//	}
//	
//	@GetMapping("/list")
//	@ApiOperation(value = "list 조회", note = "생성되어 있는 방의 list를 응답한다.")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//        @ApiResponse(code = 401, message = "인증 실패"),
//        @ApiResponse(code = 404, message = "사용자 없음"),
//        @ApiResponse(code = 500, message = "서버 오류")
//    })
//	public ResponseEntity<UserRes> getConferenceList(@ApiIgnore Authentication authentication) {
//		/**
//		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
//		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
//		 */
//		System.out.println("회의 목록 조회");
//		
//		
//		return null;
//	}	
}
