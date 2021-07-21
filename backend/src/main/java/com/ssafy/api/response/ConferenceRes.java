package com.ssafy.api.response;

import com.ssafy.db.entity.Conference;
import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ConferenceResponse")
public class ConferenceRes{
//	@ApiModelProperty(name="Conference ID")
//	String userId;
//	
//	public static ConferenceRes of(Conference conference) {
//		ConferenceRes res = new ConferenceRes();
//		
////		res.setUserId(user.getUserId());
//		return res;
//	}
}
