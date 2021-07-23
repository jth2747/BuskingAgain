package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.BuskingCreatePostReq;
import com.ssafy.db.entity.Busking;

public interface BuskingService {

	Busking createBusking(BuskingCreatePostReq buskingCreatInfo, Long owner_id);

	Busking getBuskingByBuskingId(Long id);

	String getGenreById(long busking_genre);

	List<Busking> list();

	Busking ModifyBusking(BuskingCreatePostReq buskingModifyInfo, Long ownerId, Long buskingId);

	List<Busking> listGenre(String genre);

	Busking deleteBusking(Long buskingId, Busking busking);


}
