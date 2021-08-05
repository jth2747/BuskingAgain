package com.ssafy.api.response;

import com.ssafy.db.entity.Busking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBuskingRes {
	
	int viewers;
	int max_viewers;
	boolean owner;
	String ownerId;
	String userId;
	String busking_genre;
	String title;
	String description;
	String thumbnail_url;

	public static UserBuskingRes of(Busking busking, String userid, String genre) {
		UserBuskingRes res = new UserBuskingRes();
		res.setOwnerId(userid);
		res.setBusking_genre(genre);
		res.setTitle(busking.getTitle());
		res.setDescription(busking.getDescription());
		res.setThumbnail_url(busking.getThumbnail_url());
		res.setViewers(busking.getViewers());
		res.setMax_viewers(busking.getMax_viewers());
		return res;
		
	}
}
