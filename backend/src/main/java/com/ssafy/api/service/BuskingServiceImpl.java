package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.BuskingCreatePostReq;
import com.ssafy.db.entity.Busking;
import com.ssafy.db.entity.Busking_genre;
import com.ssafy.db.repository.BuskingGenreRepository;
import com.ssafy.db.repository.BuskingGenreRepositorySupport;
import com.ssafy.db.repository.BuskingRepository;

@Service("buskingService")
public class BuskingServiceImpl implements BuskingService {

	@Autowired
	BuskingRepository buskingRespository;
	
	@Autowired
	BuskingGenreRepository buskingGenreRespository;
	
	@Autowired
	BuskingGenreRepositorySupport buskingGenreRepositorySupport;
	
	@Override
	public Busking createBusking(BuskingCreatePostReq buskingCreatInfo, Long owner_id) {
		// TODO Auto-generated method stub
		System.out.println("버스킹생성 서비스 입장");
		Busking busking = new Busking();
		busking.setTitle(buskingCreatInfo.getTitle());
		busking.setDescription(buskingCreatInfo.getDescription());
		busking.setThumbnail_url(buskingCreatInfo.getThumbnailurl());
		busking.setOwner_id(owner_id);
		busking.setIs_active(1);
		busking.setLikes(0);
		busking.setViewers(0);
		
		if(buskingCreatInfo.getGenre() != null) {
			Long genreId = buskingGenreRepositorySupport.findGenreByGenreName(buskingCreatInfo.getGenre()).getId();
			System.out.println(genreId);
			busking.setBusking_genre(genreId);
			return buskingRespository.save(busking);
		}else
			return null;
	}

	@Override
	public Busking getBuskingByBuskingId(Long id) {
		// TODO Auto-generated method stub
		Busking busking = buskingRespository.getOne(id);
		return busking;
	}

	@Override
	public String getGenreById(long busking_genre) {
		// TODO Auto-generated method stub
		Busking_genre genre = buskingGenreRespository.getOne(busking_genre);
		return genre.getName();
	}

	@Override
	public List<Busking> list() {
		// TODO Auto-generated method stub
		
		List<Busking> list = buskingRespository.findAll();
		List<Busking> ret = new ArrayList<>();
		
		for(Busking b : list) {
			if(b.getIs_active() == 1) {
				ret.add(b);
			}
		}
		return ret;
	}

}
