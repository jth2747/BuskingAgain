package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.GenreListPostReq;

public interface FollowService {

	void addGenre(GenreListPostReq genre, Long id);

	void deleteGenre(GenreListPostReq genre, Long id);

	GenreListPostReq getList(Long id);

}
