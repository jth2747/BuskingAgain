package com.ssafy.api.controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.BuskingCreatePostReq;
import com.ssafy.api.response.BuskingRes;
import com.ssafy.api.service.BuskingService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Busking;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "버스킹 API", tags = {"Busking"})
@RestController
@RequestMapping("/api/v1/busking")
public class BuskingController {

	@Autowired
	BuskingService buskingService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/create")
	@ApiOperation(value = "버스킹 생성", notes = "로그인한 회원의 방을 만든다.")
	@ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> create(@ApiIgnore Authentication authentication,
			@RequestBody BuskingCreatePostReq buskingCreatInfo){
		System.out.println("버스킹 생성 컨트롤러 들어옴");
		
//		Ownerid를 확인하기 위해서 회원정보 조회
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		System.out.println("Ownerid :" + user.getId());
		
		
//		busking 방 정보
		System.out.println(buskingCreatInfo.getTitle());
		Busking busking = buskingService.createBusking(buskingCreatInfo, user.getId());
		
		if(busking == null)
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "버스킹 정보가 null"));
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "버스킹 생성 Success"));
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "방 정보 조회", notes = "선택한 방의 정보를 응답한다.") 
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BuskingRes> getBuskingInfo(@PathVariable Long id) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("방정보 조회");
		Busking busking = buskingService.getBuskingByBuskingId(id);
		System.out.println(busking.getId());
		String userid = userService.getUserIdById(busking.getOwner_id());
		System.out.println(userid);
		String genre = buskingService.getGenreById(busking.getBusking_genre());
		return ResponseEntity.status(200).body(BuskingRes.of(busking, userid, genre));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "list 조회", notes = "생성되어 있는 방(is_active)의 list를 응답한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<List<Busking>> getBuskingList() {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("진행중인 버스킹 목록 조회");
		
		List<Busking> list = buskingService.list();
	
		
		return new ResponseEntity<List<Busking>>(list, HttpStatus.OK);
	}	
	@PutMapping("/modify")
	@ApiOperation(value = "방 정보 수정", notes = "선택한 방의 정보 수정을 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<BuskingRes> modifyBuskingInfo(@RequestBody @ApiParam(value="방수정 정보", required = true) BuskingCreatePostReq buskingModifyInfo) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("방정보 수정");
		
		return null;
	}
	
}
