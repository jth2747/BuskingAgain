package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ConferenceCreatePostRequest")
public class ConferenceCreatePostReq {
	@ApiModelProperty(name="버스킹 title", example="title")
	String title;
	@ApiModelProperty(name="버스킹 decription", example="decription")
	String decription;
	@ApiModelProperty(name="버스킹 thumbnail_url", example="thumbnail_url")
	String thumbnail_url;
	@ApiModelProperty(name="owner id", example="owener id")
	Long owner_id;
	@ApiModelProperty(name="버스킹 genre", example="genre")
	String conference_genre;
}

