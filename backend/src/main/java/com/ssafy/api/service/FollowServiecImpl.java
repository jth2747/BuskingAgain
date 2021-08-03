package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.db.entity.Busking_genre;
import com.ssafy.db.entity.Fav_genre;
import com.ssafy.db.repository.BuskingGenreRepository;
import com.ssafy.db.repository.BuskingGenreRepositorySupport;
import com.ssafy.db.repository.FollowRepository;
import com.ssafy.db.repository.FollowRepositorySupport;

@Service("followService")
public class FollowServiecImpl implements FollowService {

	@Autowired
	BuskingGenreRepository buskingGenreRespository;
	@Autowired
	BuskingGenreRepositorySupport buskingGenreRepositorySupport;
	
	@Autowired
	FollowRepository followRepository;
	@Autowired
	FollowRepositorySupport followRepositorySupport;
	
	@Override
	public void addGenre(List<String> genre, Long u_id) {
		// TODO Auto-generated method stub
		
		System.out.println("장르 추가 서비스");
		
		for(String g : genre) {
			long g_id = buskingGenreRepositorySupport.findGenreByGenreName(g).getId();
			if(followRepositorySupport.findFavGenreByGid(u_id, g_id)==null) {
				Fav_genre fav_genre = new Fav_genre();
				fav_genre.setU_id(u_id);
				fav_genre.setG_id(g_id);
				followRepository.save(fav_genre);
			}
		}
	}

	@Override
	public void deleteGenre(List<String> genre, Long u_id) {
		// TODO Auto-generated method stub
		
		System.out.println("장르 삭제 서비스");
		
		for(String g : genre) {
			long g_id = buskingGenreRepositorySupport.findGenreByGenreName(g).getId();
			if(followRepositorySupport.findFavGenreByGid(u_id, g_id) != null) {
				Fav_genre fav_genre = followRepositorySupport.findFavGenreByGid(u_id, g_id);
				followRepository.delete(fav_genre);
			}
		}
		
	}

	@Override
	public List<String> getList(Long u_id) {
		List<Fav_genre> list = followRepository.findAll();
		List<String> ret = new ArrayList<String>();
		
		for(Fav_genre fg : list) {
			if(fg.getU_id() == u_id) {				
				Optional<Busking_genre> busking_genre = buskingGenreRespository.findById(fg.getG_id());
				if(busking_genre.isPresent()) {
					Busking_genre bg = busking_genre.get();
					String genre = bg.getName();
					ret.add(genre);
				}
			}
		}
		
		return ret;
	}

}
