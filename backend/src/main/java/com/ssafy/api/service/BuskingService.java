package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.BuskingCreatePostReq;
import com.ssafy.api.response.BuskingListRes;
import com.ssafy.api.response.LikeRes;
import com.ssafy.api.response.UserBuskingRes;
import com.ssafy.db.entity.Busking;
import com.ssafy.db.entity.User_busking;

public interface BuskingService {

	Busking createBusking(BuskingCreatePostReq buskingCreatInfo, Long owner_id);

	Busking getBuskingByBuskingId(Long id);

	String getGenreById(long busking_genre);

	List<BuskingListRes> list();

	Busking ModifyBusking(BuskingCreatePostReq buskingModifyInfo, Long ownerId, Long buskingId, Busking originbusking);

	List<BuskingListRes> listGenre(String genre);

	Busking deleteBusking(Long buskingId, Busking busking);

	User_busking enterBusking(Long userid, Long buskingId);

	Busking outBusking(Long userId, Long buskingId);

	LikeRes likeBusking(Long userId, Long buskingId);


}
