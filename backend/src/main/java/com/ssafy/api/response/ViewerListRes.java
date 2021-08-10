package com.ssafy.api.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ViewerListResponse")
public class ViewerListRes {

	List<String> viewersList;
}
