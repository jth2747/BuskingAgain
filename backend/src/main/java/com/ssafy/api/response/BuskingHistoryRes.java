package com.ssafy.api.response;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BuskingHistoryResponse")
public class BuskingHistoryRes {
	long id;
	String ownerId;
	String busking_genre;
	String title;
	String description;
	String thumbnail_url;
	String start_time;
	String end_time;
	int likes;
	int viewers;
	int max_viewers;
}