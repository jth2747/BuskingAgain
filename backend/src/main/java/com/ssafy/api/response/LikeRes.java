package com.ssafy.api.response;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LikeResponse")
public class LikeRes {

	int likes;
	boolean check;
}
