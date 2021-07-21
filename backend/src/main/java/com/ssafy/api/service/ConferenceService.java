package com.ssafy.api.service;

import org.springframework.stereotype.Service;

import com.ssafy.api.request.ConferenceCreatePostReq;
import com.ssafy.db.entity.Conference;


public interface ConferenceService {

	Conference createConference(ConferenceCreatePostReq conferenceCreateInfo, Long owner_id);
}
