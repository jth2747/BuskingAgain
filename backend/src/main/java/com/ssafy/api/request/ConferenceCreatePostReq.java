package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ConferenceCreatePostRequest")
public class ConferenceCreatePostReq {
	@ApiModelProperty(name="title", example="title")
	String title;
	@ApiModelProperty(name="genre", example="genre")
	String genre;
	@ApiModelProperty(name="decription", example="decription")
	String decription;
	@ApiModelProperty(name="thumbnail_url", example="thumbnail_url")
	String thumbnail_url;
//	@ApiModelProperty(name="owner_id", example="owener id")
//	Long owner_id;
}

