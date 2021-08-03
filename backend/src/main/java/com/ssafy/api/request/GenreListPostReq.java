package com.ssafy.api.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("GenreListPostRequest")
public class GenreListPostReq {
	List<String> genreList;
}
