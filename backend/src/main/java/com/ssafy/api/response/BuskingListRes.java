package com.ssafy.api.response;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BuskingListResponse")
public class BuskingListRes {

	String ownerId;
	String busking_genre;
	String title;
	String description;
	String thumbnail_url;
	Timestamp start_time;
	int likes;
	int viewers;
}
