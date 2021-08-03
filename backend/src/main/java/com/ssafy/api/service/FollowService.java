package com.ssafy.api.service;

import java.util.List;

public interface FollowService {

	void addGenre(List<String> genre, Long id);

	void deleteGenre(List<String> genre, Long id);

	List<String> getList(Long id);

}
