package com.ssafy.api.controller;

import java.util.List;


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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import com.ssafy.api.request.BuskingCreatePostReq;
import com.ssafy.api.request.KickOutPostReq;
import com.ssafy.api.request.SearchPostReq;
import com.ssafy.api.response.BuskingCreateRes;
import com.ssafy.api.response.BuskingHistoryRes;
import com.ssafy.api.response.BuskingListRes;
import com.ssafy.api.response.BuskingRes;
import com.ssafy.api.response.LikeRes;
import com.ssafy.api.response.UserBuskingRes;
import com.ssafy.api.response.ViewerListRes;
import com.ssafy.api.service.BuskingService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Busking;
import com.ssafy.db.entity.Fav_genre;
import com.ssafy.db.entity.User;
import com.ssafy.db.entity.User_busking;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
	public ResponseEntity<BuskingCreateRes> create(@ApiIgnore Authentication authentication,
			@RequestBody BuskingCreatePostReq buskingCreatInfo){
		System.out.println("버스킹 생성 컨트롤러 들어옴");
		
//		Ownerid를 확인하기 위해서 회원정보 조회
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		System.out.println("Ownerid :" + user.getId());
		
		
//		busking 방 정보
		System.out.println(buskingCreatInfo.toString());
		Busking busking = buskingService.createBusking(buskingCreatInfo, user.getId());
		
		if(busking == null)
			return new ResponseEntity<BuskingCreateRes>(HttpStatus.BAD_REQUEST);

		BuskingCreateRes ret = new BuskingCreateRes();
		ret.setId(busking.getId());
		return new ResponseEntity<BuskingCreateRes>(ret, HttpStatus.OK);

//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "버스킹 생성 Success"));
	}
	
	@PatchMapping("/{buskingId}")
	@ApiOperation(value = "방 정보 수정", notes = "선택한 방의 정보 수정을 응답한다.") 
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> modifyBuskingInfo(@ApiIgnore Authentication authentication, @PathVariable Long buskingId,
			@RequestBody @ApiParam(value="방수정 정보", required = true) BuskingCreatePostReq buskingModifyInfo) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("방정보 수정");
		System.out.println(authentication);
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		System.out.println("Ownerid :" + user.getId());
		
		System.out.println(buskingModifyInfo.toString());
		Busking originbusking = buskingService.getBuskingByBuskingId(buskingId);
		Busking busking = buskingService.ModifyBusking(buskingModifyInfo, user.getId(), buskingId, originbusking);
		
		if(busking == null)
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "버스킹 정보가 null"));
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "버스킹 생성 Success"));
		
	}
	@GetMapping("/list")
	@ApiOperation(value = "list 조회", notes = "생성되어 있는 방(is_active)의 list를 응답한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<List<BuskingListRes>> getBuskingList() {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("진행중인 버스킹 목록 조회");
		List<BuskingListRes> list = buskingService.list();
	
		
		return new ResponseEntity<List<BuskingListRes>>(list, HttpStatus.OK);
	}	
	
	@GetMapping("/genrelist")
	@ApiOperation(value = "list 조회", notes = "생성되어 있는 방(장르별)의 list를 응답한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<List<BuskingListRes>> getBuskingGenreList(@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("진행중인 버스킹 장르별 목록 조회");
		
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		
		List<BuskingListRes> list = buskingService.listGenre(user.getId());
		
		return new ResponseEntity<List<BuskingListRes>>(list, HttpStatus.OK);
	}	
	
	@DeleteMapping("/{buskingId}")
	@ApiOperation(value = "busking 종료", notes = "생성되어 있는 방을 종료한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> deleteBusking(@ApiIgnore Authentication authentication, @PathVariable Long buskingId) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("버스킹 종료 or 퇴장");
		
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String user_id = userDetails.getUsername();
		User user = userService.getUserByUserId(user_id);
		Long userId = user.getId();
		
		Busking busking = buskingService.getBuskingByBuskingId(buskingId);
		Long ownerId = busking.getOwner_id();
		
		if(userId == ownerId) {
			System.out.println("버스킹 종료");
			busking = buskingService.deleteBusking(buskingId, busking);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "버스킹 종료 Success"));
		}else {
			System.out.println("버스킹 퇴장");
			busking = buskingService.outBusking(userId, buskingId);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "버스킹 퇴장 Success"));

		}
//		return null;
	}
	
	@GetMapping("/{buskingId}")
	@ApiOperation(value = "방 입장", notes = "선택한 방으로 입장한다.") 
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<UserBuskingRes> enterBusking(@ApiIgnore Authentication authentication, @PathVariable Long buskingId) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("방 입장");
		Busking busking = buskingService.getBuskingByBuskingId(buskingId);
		System.out.println(busking.getId());
		
//		return ResponseEntity.status(200).body(BuskingRes.of(busking, userid, genre));
		
		
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String user_id = userDetails.getUsername();
		User user = userService.getUserByUserId(user_id);
		Long userId = user.getId();
		
		String userid = userService.getUserIdById(busking.getOwner_id());
		System.out.println(userid);
		String genre = buskingService.getGenreById(busking.getBusking_genre());
		
		UserBuskingRes userBuskingRes = UserBuskingRes.of(busking, userid, genre);
		
		if(userId != busking.getOwner_id() && busking.getIs_active() == 1) {
			User_busking user_busking  = buskingService.enterBusking(user.getId(), buskingId);
			if(user_busking == null) {
				return new ResponseEntity<UserBuskingRes>(HttpStatus.BAD_REQUEST);
			}
			userBuskingRes.setOwner(false);
			busking = buskingService.getBuskingByBuskingId(buskingId);
			userBuskingRes.setViewers(busking.getViewers());
			userBuskingRes.setUserId(user_id);
			return new ResponseEntity<UserBuskingRes>(userBuskingRes, HttpStatus.OK);
		}
		else if(userId == busking.getOwner_id()&& busking.getIs_active() == 1) {
			User_busking user_busking  = buskingService.enterBusking(user.getId(), buskingId);
			userBuskingRes.setOwner(true);
			userBuskingRes.setViewers(busking.getViewers());
			userBuskingRes.setUserId(user_id);
			return new ResponseEntity<UserBuskingRes>(userBuskingRes, HttpStatus.OK);
		}
		
		return new ResponseEntity<UserBuskingRes>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/{buskingId}")
	@ApiOperation(value = "좋아요 누름", notes = "좋아요 누르는 기능.") 
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<LikeRes> likeBusking(@ApiIgnore Authentication authentication, @PathVariable Long buskingId) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("좋아요 입장");
		Busking busking = buskingService.getBuskingByBuskingId(buskingId);
		System.out.println(busking.getId());
		
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String user_id = userDetails.getUsername();
		User user = userService.getUserByUserId(user_id);
		Long userId = user.getId();
		
		LikeRes likeRes = buskingService.likeBusking(userId, buskingId);
		
		
		return new ResponseEntity<LikeRes>(likeRes, HttpStatus.OK);
	}
	
	@GetMapping("/search/{title}")
	@ApiOperation(value = "list 조회", notes = "생성되어 있는 방(is_active)의 list를 응답한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<List<BuskingListRes>> getBuskingList(@PathVariable String title) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("진행중인 버스킹 검색");
		
		List<BuskingListRes> list = buskingService.searchList(title);
	
		
		return new ResponseEntity<List<BuskingListRes>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/viewers/{buskingId}")
	@ApiOperation(value = "list 조회", notes = "현재 방의 viewers를 응답한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<ViewerListRes> getViewersList(@ApiIgnore Authentication authentication, @PathVariable Long buskingId)  {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("버스킹 viewers목록 불러오기");
		
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String user_id = userDetails.getUsername();
		User user = userService.getUserByUserId(user_id);
		Long userId = user.getId();

		Busking busking = buskingService.getBuskingByBuskingId(buskingId);
		
		if(busking.getOwner_id() == userId) {			
			ViewerListRes viewersList = buskingService.viewersList(buskingId);
			return new ResponseEntity<ViewerListRes>(viewersList, HttpStatus.OK);
		}else {
			System.out.println("방장 아님...못봄");
			return new ResponseEntity<ViewerListRes>(HttpStatus.BAD_REQUEST);
		}
		
	}	
	@PostMapping("/kickout/{buskingId}")
	@ApiOperation(value = "list 조회", notes = "현재 방의 viewers를 응답한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> kickout(@ApiIgnore Authentication authentication, @PathVariable Long buskingId,
			@RequestBody @ApiParam(value="방 강퇴", required = true) KickOutPostReq kickoutList)  {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("버스킹 강퇴 시키기");
		
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String user_id = userDetails.getUsername();
		User user = userService.getUserByUserId(user_id);
		Long userId = user.getId();
		
		Busking busking = buskingService.getBuskingByBuskingId(buskingId);
		
		System.out.println(user_id);
		
		if(busking.getOwner_id() == userId) {			
			buskingService.kickout(buskingId, kickoutList.getKickoutIdList());
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "강퇴 Success"));	
		}else {
			System.out.println("방장 아님...못봄");
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "방장아님 강퇴 권한x"));
		}
		
	}	
	@GetMapping("/rankperson")
	@ApiOperation(value = "list 조회", notes = "현재 방의 viewers를 응답한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<BuskingListRes>> getBuskingListSortByViewers()  {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("버스킹 목록 접속자순 정렬 불러오기");
		
		List<BuskingListRes> list = buskingService.sortByViewers();
	
		
		return new ResponseEntity<List<BuskingListRes>>(list, HttpStatus.OK);
	}	
	@GetMapping("/ranklike")
	@ApiOperation(value = "list 조회", notes = "현재 방의 viewers를 응답한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<BuskingListRes>> getBuskingListSortByLikes()  {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("버스킹 목록 접속자순 정렬 불러오기");
		
		List<BuskingListRes> list = buskingService.sortByLikes();
		
		
		return new ResponseEntity<List<BuskingListRes>>(list, HttpStatus.OK);
	}	
	@GetMapping("/history")
	@ApiOperation(value = "과거 list 조회", notes = "과거에 생성했던 버스킹 목록 불러오기")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<BuskingHistoryRes>> getBuskingHistory(@ApiIgnore Authentication authentication)  {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("버스킹 과거 목록 불러오기");
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String user_id = userDetails.getUsername();
		User user = userService.getUserByUserId(user_id);
		Long userId = user.getId();
		
		List<BuskingHistoryRes> list = buskingService.history(userId);
		
		
		return new ResponseEntity<List<BuskingHistoryRes>>(list, HttpStatus.OK);
	}	
	
	@GetMapping("/randomlist")
	@ApiOperation(value = "list 랜덤 조회", notes = "버스킹 목록 랜덤 6개 불러오기")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "사용자 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<BuskingListRes>> getBuskingListRandom()  {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println("버스킹 과거 목록 불러오기");
	
		List<BuskingListRes> list = buskingService.random();
		
		
		return new ResponseEntity<List<BuskingListRes>>(list, HttpStatus.OK);
	}	
}
