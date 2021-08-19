package com.ssafy.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.api.request.GenreListPostReq;
import com.ssafy.api.response.BuskingListRes;
import com.ssafy.api.response.GenreListRes;
import com.ssafy.api.service.FollowService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "팔로우 API", tags = {"Follow"})
@RestController
@RequestMapping("/api/v1/genre")
public class FollowController {
	
	@Autowired
	FollowService followService;
	
	@Autowired
	UserService userService;
	
	@PostMapping()
	@ApiOperation(value = "팔로우 추가", notes = "로그인한 회원의 팔로우 목록을 추가.")
	@ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> addFollow(@ApiIgnore Authentication authentication,
			@RequestBody GenreListPostReq genre){
//		System.out.println("장르 팔로우 컨트롤러 들어옴");
		
//		userid를 확인하기 위해서 회원정보 조회
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
//		System.out.println("userid :" + user.getId());
		
		followService.addGenre(genre, user.getId());
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Follow 추가 Success"));

	}
	
	
//	@DeleteMapping()
//	@ApiOperation(value = "팔로우 삭제", notes = "로그인한 회원의 팔로우 목록을 삭제.")
//	@ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//        @ApiResponse(code = 401, message = "인증 실패"),
//        @ApiResponse(code = 404, message = "사용자 없음"),
//        @ApiResponse(code = 500, message = "서버 오류")
//    })
//	public ResponseEntity<? extends BaseResponseBody> deleteFollow(@ApiIgnore Authentication authentication,
//			@RequestBody GenreListPostReq genre){
//		System.out.println("장르 팔로우 삭제 컨트롤러 들어옴");
//		
////		userid를 확인하기 위해서 회원정보 조회
//		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
//		String userId = userDetails.getUsername();
//		User user = userService.getUserByUserId(userId);
//		System.out.println("userid :" + user.getId());
//		
//		followService.deleteGenre(genre, user.getId());
//		
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Follow 삭제 Success"));
//
//	}
	
	
	@GetMapping("/list")
	@ApiOperation(value = "팔로우 조회", notes = "로그인한 회원의 팔로우 목록을 조회.")
	@ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<GenreListRes> getFollowList(@ApiIgnore Authentication authentication){
//		System.out.println("장르 팔로우 리스트 컨트롤러 들어옴");
		
//		userid를 확인하기 위해서 회원정보 조회
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
//		System.out.println("userid :" + user.getId());
		
		GenreListRes list = followService.getList(user.getId());
		
		return new ResponseEntity<GenreListRes>(list, HttpStatus.OK);

	}

}
