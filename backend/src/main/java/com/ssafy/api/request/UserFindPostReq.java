package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFindPostReqest")
public class UserFindPostReq {
	
	@ApiModelProperty(name="유저 Name", example="name")
	String name;
	@ApiModelProperty(name="유저 Email", example="email")
	String email;
}
