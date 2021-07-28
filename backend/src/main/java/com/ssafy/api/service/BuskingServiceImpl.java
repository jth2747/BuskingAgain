package com.ssafy.api.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.BuskingCreatePostReq;
import com.ssafy.api.response.BuskingListRes;
import com.ssafy.db.entity.Busking;
import com.ssafy.db.entity.Busking_genre;
import com.ssafy.db.repository.BuskingGenreRepository;
import com.ssafy.db.repository.BuskingGenreRepositorySupport;
import com.ssafy.db.repository.BuskingRepository;
import com.ssafy.db.repository.UserRepository;

@Service("buskingService")
public class BuskingServiceImpl implements BuskingService {

	@Autowired
	BuskingRepository buskingRespository;
	
	@Autowired
	BuskingGenreRepository buskingGenreRespository;
	
	@Autowired
	BuskingGenreRepositorySupport buskingGenreRepositorySupport;
	
	@Autowired
	UserRepository userRepository;
	
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
		busking.setStart_time(Timestamp.valueOf(LocalDateTime.now()));

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
	public List<BuskingListRes> list() {
		// TODO Auto-generated method stub
		
		List<Busking> list = buskingRespository.findAll();
		List<BuskingListRes> ret = new ArrayList<>();
		
		for(Busking b : list) {
			if(b.getIs_active() == 1) {
				BuskingListRes input = new BuskingListRes();
				input.setDescription(b.getDescription());
				input.setLikes(b.getLikes());
				input.setTitle(b.getTitle());
				input.setViewers(b.getViewers());
				input.setStart_time(b.getStart_time());
				input.setThumbnail_url(b.getThumbnail_url());
				
				String genrename = buskingGenreRespository.getOne(b.getBusking_genre()).getName();
				String useridString = userRepository.getOne(b.getOwner_id()).getUserId();
				
				ret.add(input);
			}
		}
		return ret;
	}

	@Override
	public Busking ModifyBusking(BuskingCreatePostReq buskingModifyInfo, Long ownerId, Long buskingId, Busking originbusking) {
		// TODO Auto-generated method stub
		Busking busking = new Busking();
		busking.setId(buskingId);
		busking.setOwner_id(ownerId);
		busking.setTitle(buskingModifyInfo.getTitle());
//		busking.setBusking_genre(buskingModifyInfo.getGenre());
		busking.setDescription(buskingModifyInfo.getDescription());
		busking.setIs_active(originbusking.getIs_active());
		busking.setLikes(originbusking.getLikes());
		busking.setStart_time(originbusking.getStart_time());
		Long genreId = buskingGenreRepositorySupport.findGenreByGenreName(buskingModifyInfo.getGenre()).getId();
		System.out.println(genreId);
		busking.setBusking_genre(genreId);
		
		return buskingRespository.save(busking);
	}

	@Override
	public List<BuskingListRes> listGenre(String genre) {
		// TODO Auto-generated method stub
		List<Busking> list = buskingRespository.findAll();
		List<BuskingListRes> ret = new ArrayList<>();
		
		Long buskingGenre = buskingGenreRepositorySupport.findGenreByGenreName(genre).getId();
		
		for(Busking b : list) {
			if(b.getIs_active() == 1 && b.getBusking_genre() == buskingGenre) {
				BuskingListRes input = new BuskingListRes();
				input.setDescription(b.getDescription());
				input.setLikes(b.getLikes());
				input.setTitle(b.getTitle());
				input.setViewers(b.getViewers());
				input.setStart_time(b.getStart_time());
				input.setThumbnail_url(b.getThumbnail_url());
				
				String genrename = buskingGenreRespository.getOne(b.getBusking_genre()).getName();
				String useridString = userRepository.getOne(b.getOwner_id()).getUserId();
				
				ret.add(input);
			}
		}
		return ret;
	}

	@Override
	public Busking deleteBusking(Long buskingId, Busking busking) {
		// TODO Auto-generated method stub
		busking.setIs_active(0);
		busking.setEnd_time(Timestamp.valueOf(LocalDateTime.now()));
		
		return buskingRespository.save(busking);
	}

}
