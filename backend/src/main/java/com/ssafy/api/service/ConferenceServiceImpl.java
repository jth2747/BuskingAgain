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
		System.out.println("방생성 서비스 들어옴"+" "+ owner_id);
		conference.setTitle(conferenceCreateInfo.getTitle());
		conference.setDescription(conferenceCreateInfo.getDecription());
		conference.setThumbnail_url(conferenceCreateInfo.getThumbnail_url());
		conference.setOwner_id(owner_id);
		conference.setIs_active(1);//방 활성화
		System.out.println(conference.getTitle());
		if(conferenceCreateInfo.getGenre() != null) {
			long genre_id = conferenceGenreRepositorySupport.findGenreByGenreName(conferenceCreateInfo.getGenre()).getId();
			System.out.println(genre_id);
			conference.setConference_genre(genre_id);			
		}else {
			System.out.println("장르null");
//			conference.setConference_genre(1);
			
		}
		
//		회원정보 조회
		
		return conferenceRespository.save(conference);
	}

}
