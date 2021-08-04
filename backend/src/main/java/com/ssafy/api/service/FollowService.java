package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.GenreListPostReq;
import com.ssafy.api.response.GenreListRes;

public interface FollowService {

	void addGenre(GenreListPostReq genre, Long id);

//	void deleteGenre(GenreListPostReq genre, Long id);

	GenreListRes getList(Long id);

}
