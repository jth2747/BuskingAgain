package com.ssafy.api.response;

import com.ssafy.db.entity.Busking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BuskingResponse")
public class BuskingRes {
	
	@ApiModelProperty(name = "Busking ID")
	String ownerId;
	String busking_genre;
	String title;
	String description;
	String thumbnail_url;
	
	public static BuskingRes of(Busking busking, String userid, String genre) {
		BuskingRes res = new BuskingRes();
		res.setOwnerId(userid);
		res.setBusking_genre(genre);
		res.setTitle(busking.getTitle());
		res.setDescription(busking.getDescription());
		res.setThumbnail_url(busking.getThumbnail_url());
		return res;
		
	}

}
