package com.ssafy.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.ConferenceCreatePostReq;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.entity.Conference_genre;
import com.ssafy.db.repository.ConferenceGenreRepositorySupport;
import com.ssafy.db.repository.ConferenceRepository;

@Service("conferenceService")
public class ConferenceServiceImpl implements ConferenceService {

	@Autowired
	ConferenceRepository conferenceRespository;
	
	@Autowired
	ConferenceRepository conferenceGenreRepository;
	
	@Autowired
	ConferenceGenreRepositorySupport conferenceGenreRepositorySupport;
	
	@Override
	public Conference createConference(ConferenceCreatePostReq conferenceCreateInfo, Long owner_id) {
		// TODO Auto-generated method stub
		Conference conference = new Conference();
		
		conference.setTitle(conferenceCreateInfo.getTitle());
		conference.setDescription(conferenceCreateInfo.getDecription());
		conference.setThumbnail_url(conferenceCreateInfo.getThumbnail_url());
		
		long genre_id = conferenceGenreRepositorySupport.findGenreByGenreName(conferenceCreateInfo.getConference_genre()).getId();
		conference.setConference_genre(genre_id);
//		회원정보 조회
		conference.setOwner_id(owner_id);
		
		return conferenceRespository.save(conference);
	}

}
