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
	String thumbnailurl;
	@Override
	public String toString() {
		return "BuskingCreatePostReq [title=" + title + ", genre=" + genre + ", description=" + description
				+ ", thumbnailurl=" + thumbnailurl + "]";
	}
}