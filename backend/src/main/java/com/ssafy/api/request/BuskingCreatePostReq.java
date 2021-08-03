package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BuskingCreatePostRequest")
public class BuskingCreatePostReq {

	String title;
	String genre;
	String description;
	String thumbnail_url;
	int max_viewers;
	@Override
	public String toString() {
		return "BuskingCreatePostReq [title=" + title + ", genre=" + genre + ", description=" + description
				+ ", thumbnail_url=" + thumbnail_url + ", max_viewers=" + max_viewers + "]";
	}

}
