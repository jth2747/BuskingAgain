package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserPasswordFindPostReq")
public class UserPasswordFindPostReq {
	
	@ApiModelProperty(name="유저 Name", example="name")
	String name;
	@ApiModelProperty(name="유저 Email", example="email")
	String email;
	@ApiModelProperty(name="유저 id", example="id")
	String userid;
}
