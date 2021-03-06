package com.ssafy.api.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.BuskingCreatePostReq;
import com.ssafy.api.request.SearchPostReq;
import com.ssafy.api.response.BuskingHistoryRes;
import com.ssafy.api.response.BuskingListRes;
import com.ssafy.api.response.LikeRes;
import com.ssafy.api.response.UserBuskingRes;
import com.ssafy.api.response.ViewerListRes;
import com.ssafy.db.entity.Busking;
import com.ssafy.db.entity.Busking_genre;
import com.ssafy.db.entity.Fav_genre;
import com.ssafy.db.entity.Kickout_info;
import com.ssafy.db.entity.Liked;
import com.ssafy.db.entity.User;
import com.ssafy.db.entity.User_busking;
import com.ssafy.db.repository.BuskingGenreRepository;
import com.ssafy.db.repository.BuskingGenreRepositorySupport;
import com.ssafy.db.repository.BuskingRepository;
import com.ssafy.db.repository.BuskingRepositorySupport;
import com.ssafy.db.repository.FollowRepository;
import com.ssafy.db.repository.FollowRepositorySupport;
import com.ssafy.db.repository.KickoutRepository;
import com.ssafy.db.repository.KickoutRepositorySupport;
import com.ssafy.db.repository.LikedRepository;
import com.ssafy.db.repository.LikedRepositorySupport;
import com.ssafy.db.repository.UserBuskingRepository;
import com.ssafy.db.repository.UserBuskingRepositorySupport;
import com.ssafy.db.repository.UserRepository;

@Service("buskingService")
public class BuskingServiceImpl implements BuskingService {

	@Autowired
	BuskingRepository buskingRespository;
	@Autowired
	BuskingRepositorySupport buskingRespositorySupport;
	
	@Autowired
	BuskingGenreRepository buskingGenreRespository;
	@Autowired
	BuskingGenreRepositorySupport buskingGenreRepositorySupport;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserBuskingRepository userBuskingRepository;
	@Autowired
	UserBuskingRepositorySupport userBuskingRepositorySupport;
	
	@Autowired
	LikedRepository likedRepository;
	@Autowired
	LikedRepositorySupport likedRepositorySupport;
	
	@Autowired
	FollowRepository followRepository;
	@Autowired
	FollowRepositorySupport followRepositorySupport;
	
	@Autowired
	KickoutRepository kickoutRepository;
	@Autowired
	KickoutRepositorySupport kickoutRepositorySupport;
	
	
	@Override
	public Busking createBusking(BuskingCreatePostReq buskingCreatInfo, Long owner_id) {
		// TODO Auto-generated method stub
//		System.out.println("??????????????? ????????? ??????");
		
		if(buskingRespositorySupport.findBuskingByOwnerId(owner_id) != null) {
			List<Busking> busking = buskingRespositorySupport.findBuskingByOwnerId(owner_id);
			for(Busking b : busking) {
				if(b.getIs_active() == 1)
					return null;
				
//				System.out.println("?????????");
			}
				
		}
		
		Busking busking = new Busking();
		busking.setTitle(buskingCreatInfo.getTitle());
		busking.setDescription(buskingCreatInfo.getDescription());
		busking.setThumbnail_url(buskingCreatInfo.getThumbnail_url());
		busking.setMax_viewers(buskingCreatInfo.getMax_viewers());
		busking.setOwner_id(owner_id);

		busking.setIs_active(1);
		busking.setLikes(0);
		busking.setViewers(0);
		busking.setStart_time(Timestamp.valueOf(LocalDateTime.now()));

		
		if(buskingCreatInfo.getGenre() != null) {
			Long genreId = buskingGenreRepositorySupport.findGenreByGenreName(buskingCreatInfo.getGenre()).getId();
//			System.out.println(genreId);
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
				input.setMax_viewers(b.getMax_viewers());
				input.setId(b.getId());

				String genrename = buskingGenreRespository.getOne(b.getBusking_genre()).getName();
				input.setBusking_genre(genrename);
				String ownerID = userRepository.getOne(b.getOwner_id()).getUserId();
				input.setOwnerId(ownerID);
				
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
		busking.setThumbnail_url(buskingModifyInfo.getThumbnail_url());
//		busking.setBusking_genre(buskingModifyInfo.getGenre());
		busking.setDescription(buskingModifyInfo.getDescription());
		busking.setMax_viewers(buskingModifyInfo.getMax_viewers());
		busking.setIs_active(originbusking.getIs_active());
		busking.setLikes(originbusking.getLikes());
		busking.setStart_time(originbusking.getStart_time());
		Long genreId = buskingGenreRepositorySupport.findGenreByGenreName(buskingModifyInfo.getGenre()).getId();
//		System.out.println(genreId);
		busking.setBusking_genre(genreId);
		
		return buskingRespository.save(busking);
	}

	@Override
	public List<BuskingListRes> listGenre(long userId) {
		// TODO Auto-generated method stub
		List<Busking> list = buskingRespository.findAll();
		List<BuskingListRes> ret = new ArrayList<>();
		
		List<Fav_genre> flist = followRepository.findAll();
		for(Fav_genre f : flist) {
			if(f.getU_id() == userId) {				
				for(Busking b : list) {
					if(b.getIs_active() == 1 && b.getBusking_genre() == f.getG_id()) {
						BuskingListRes input = new BuskingListRes();
						input.setDescription(b.getDescription());
						input.setLikes(b.getLikes());
						input.setTitle(b.getTitle());
						input.setViewers(b.getViewers());
						input.setMax_viewers(b.getMax_viewers());
						input.setStart_time(b.getStart_time());
						input.setThumbnail_url(b.getThumbnail_url());
						input.setId(b.getId());
						
						String genrename = buskingGenreRespository.getOne(b.getBusking_genre()).getName();
						input.setBusking_genre(genrename);
						String ownerID = userRepository.getOne(b.getOwner_id()).getUserId();
						input.setOwnerId(ownerID);
						
						ret.add(input);
					}
				}
			}
		}
		
		
		return ret;
	}

	@Override
	public Busking deleteBusking(Long buskingId, Busking busking) {
		// TODO Auto-generated method stub
		busking.setIs_active(0);
		busking.setEnd_time(Timestamp.valueOf(LocalDateTime.now()));
		
		List<User_busking> list = userBuskingRepository.findAll();
		
		for(User_busking ub : list) {
			if(ub.getB_id() == buskingId) {
				userBuskingRepository.deleteById(ub.getId());
			}
		}
		
		return buskingRespository.save(busking);
	}
	@Override
	public Busking outBusking(Long userId, Long buskingId) {
		// TODO Auto-generated method stub
		
//		System.out.println("?????? ??????");
		User_busking user_busking = userBuskingRepositorySupport.findUser_buskingByUid(userId, buskingId);
//		System.out.println(user_busking.getB_id());
		userBuskingRepository.deleteById(user_busking.getId());
		
		Busking busking = buskingRespository.getOne(buskingId);
		int viewers = busking.getViewers()-1;
//		System.out.println(viewers+" "+busking.getViewers());
		busking.setViewers(viewers);
	
		return buskingRespository.save(busking);
	}

	public User_busking enterBusking(Long userid, Long buskingId) {
		// TODO Auto-generated method stub
		User_busking user_busking = new User_busking();
		user_busking.setU_id(userid);
		user_busking.setB_id(buskingId);
		
		Busking busking = new Busking();
		busking = buskingRespository.getOne(buskingId);
		
		if(userBuskingRepositorySupport.findUser_buskingByUid(userid, buskingId) != null) {
			user_busking = userBuskingRepositorySupport.findUser_buskingByUid(userid, buskingId);
			return user_busking;
		}
		
		if(busking.getMax_viewers()<=busking.getViewers()) {
//			System.out.println("?????? ??????");
			return null;
		}
		
//		????????? ?????? ??????
		if(kickoutRepositorySupport.findUserByUid(userid, buskingId) != null) {
//			System.out.println("?????? ??????");
			return null;
		}
		
		busking.setViewers(busking.getViewers()+1);
		Busking save = buskingRespository.save(busking);
		
		return userBuskingRepository.save(user_busking);
	}

	@Override
	public LikeRes likeBusking(Long userId, Long buskingId) {
		// TODO Auto-generated method stub
		
		Busking busking = buskingRespository.getOne(buskingId);
		LikeRes ret = new LikeRes();
	
		int likes = busking.getLikes();
		boolean check = false;
		
		
		if(likedRepositorySupport.findLikedByUid(userId, buskingId) == null) {			
			Liked liked = new Liked();
			liked.setB_id(buskingId);
			liked.setU_id(userId);
			
			likedRepository.save(liked);
			
			likes++;
			check = true;
			busking.setLikes(likes);
			buskingRespository.save(busking);
		}else {
			Liked liked = likedRepositorySupport.findLikedByUid(userId, buskingId);
			likedRepository.delete(liked);
			
			likes--;
			check = false;
			busking.setLikes(likes);
			buskingRespository.save(busking);
		}
		
		ret.setCheck(check);
		ret.setLikes(likes);
		
		return ret;
	}

	@Override
	public List<BuskingListRes> searchList(String title) {
		// TODO Auto-generated method stub
		
		List<Busking> list = buskingRespository.findAll();
		List<BuskingListRes> ret = new ArrayList<BuskingListRes>();
		
		
		for(Busking b : list) {
			if(b.getTitle().contains(title) && b.getIs_active() == 1) {
				BuskingListRes buskingListRes = new BuskingListRes();
				buskingListRes.setId(b.getId());
				buskingListRes.setTitle(b.getTitle());
				buskingListRes.setDescription(b.getDescription());
				buskingListRes.setThumbnail_url(b.getThumbnail_url());
				buskingListRes.setLikes(b.getLikes());
				buskingListRes.setMax_viewers(b.getMax_viewers());
				buskingListRes.setViewers(b.getViewers());
				buskingListRes.setStart_time(b.getStart_time());
				
				String genre = buskingGenreRespository.getOne(b.getBusking_genre()).getName();
				String ownerId = userRepository.getOne(b.getOwner_id()).getUserId();
				buskingListRes.setOwnerId(ownerId);
				buskingListRes.setBusking_genre(genre);
				
				ret.add(buskingListRes);
			}
		}
		
		return ret;
	}

	@Override
	public ViewerListRes viewersList(Long buskingId) {
		// TODO Auto-generated method stub
		
		ViewerListRes ret = new ViewerListRes();
		
		List<User_busking> userList = userBuskingRepository.findAll();
		List<String> input = new ArrayList<String>();
		
		for(User_busking ub : userList) {
			if(ub.getB_id() == buskingId) {
				User user = userRepository.findById(ub.getU_id()).get();
				input.add(user.getUserId());
			}
		}
		
		ret.setViewersList(input);
		
		return ret;
	}

	@Override
	public void kickout(Long buskingId, List<String> kickoutIdList) {
		// TODO Auto-generated method stub
		
		int cnt = 0;
		//?????? ?????? ???????????? ?????? ????????? ????????? ??????
		for(String userid : kickoutIdList) {
			User user = userRepository.findByUserId(userid).get();
//			System.out.println(user.getUserId());
			User_busking ub = userBuskingRepositorySupport.findUser_buskingByUid(user.getId(), buskingId);
			if(ub != null) {
				userBuskingRepository.delete(ub);
				
				Kickout_info kickout_info = new Kickout_info();
				kickout_info.setB_id(buskingId);
				kickout_info.setU_id(user.getId());
				kickoutRepository.save(kickout_info);
					
				cnt++;
			}
			
		}
		
		//????????? ???????????? ?????????
		Busking busking = buskingRespository.getOne(buskingId);
		int viewers = busking.getViewers()-cnt;
		busking.setViewers(viewers);
		
		
	}

	@Override
	public List<BuskingListRes> sortByViewers() {
		// TODO Auto-generated method stub
		
//		System.out.println("?????????");
		List<Busking> list = buskingRespository.findAll(Sort.by(Sort.Direction.DESC, "viewers"));
//		System.out.println(list.size());
		List<BuskingListRes> ret = new ArrayList<BuskingListRes>();
		int cnt = 0;
		for(Busking b : list) {
			if(b.getIs_active() == 1) {
				BuskingListRes buskingListRes = new BuskingListRes();
				buskingListRes.setId(b.getId());
				buskingListRes.setTitle(b.getTitle());
				buskingListRes.setDescription(b.getDescription());
				buskingListRes.setThumbnail_url(b.getThumbnail_url());
				buskingListRes.setLikes(b.getLikes());
				buskingListRes.setMax_viewers(b.getMax_viewers());
				buskingListRes.setViewers(b.getViewers());
				buskingListRes.setStart_time(b.getStart_time());
				
				String genre = buskingGenreRespository.getOne(b.getBusking_genre()).getName();
				String ownerId = userRepository.getOne(b.getOwner_id()).getUserId();
				buskingListRes.setOwnerId(ownerId);
				buskingListRes.setBusking_genre(genre);
				
				ret.add(buskingListRes);
				cnt++;
			}
			if(cnt >= 5)
				break;
		}
		return ret;
	}
	@Override
	public List<BuskingListRes> sortByLikes() {
		// TODO Auto-generated method stub
		
//		System.out.println("?????????");
		List<Busking> list = buskingRespository.findAll(Sort.by(Sort.Direction.DESC, "likes"));
//		System.out.println(list.size());
		List<BuskingListRes> ret = new ArrayList<BuskingListRes>();
		int cnt = 0;
		for(Busking b : list) {
			if(b.getIs_active() == 1) {
				BuskingListRes buskingListRes = new BuskingListRes();
				buskingListRes.setId(b.getId());
				buskingListRes.setTitle(b.getTitle());
				buskingListRes.setDescription(b.getDescription());
				buskingListRes.setThumbnail_url(b.getThumbnail_url());
				buskingListRes.setLikes(b.getLikes());
				buskingListRes.setMax_viewers(b.getMax_viewers());
				buskingListRes.setViewers(b.getViewers());
				buskingListRes.setStart_time(b.getStart_time());
				
				String genre = buskingGenreRespository.getOne(b.getBusking_genre()).getName();
				String ownerId = userRepository.getOne(b.getOwner_id()).getUserId();
				buskingListRes.setOwnerId(ownerId);
				buskingListRes.setBusking_genre(genre);
				
				ret.add(buskingListRes);
				cnt++;
			}
			if(cnt >= 5)
				break;
		}
		return ret;
	}

	@Override
	public List<BuskingHistoryRes> history(Long userId) {
		// TODO Auto-generated method stub
		List<Busking> list = buskingRespository.findAll();
		List<BuskingHistoryRes> ret = new ArrayList<BuskingHistoryRes>();
		
		for(Busking b : list) {
			if(b.getOwner_id() == userId && b.getIs_active() == 0) {
				BuskingHistoryRes buskingListRes = new BuskingHistoryRes();
				buskingListRes.setId(b.getId());
				buskingListRes.setTitle(b.getTitle());
				buskingListRes.setDescription(b.getDescription());
				buskingListRes.setThumbnail_url(b.getThumbnail_url());
				buskingListRes.setLikes(b.getLikes());
				buskingListRes.setMax_viewers(b.getMax_viewers());
				buskingListRes.setViewers(b.getViewers());
				buskingListRes.setStart_time(b.getStart_time().toString());
				buskingListRes.setEnd_time(b.getEnd_time().toString());
				
//				System.out.println(buskingListRes.getStart_time().toString());
				
				String genre = buskingGenreRespository.getOne(b.getBusking_genre()).getName();
				String ownerId = userRepository.getOne(b.getOwner_id()).getUserId();
				buskingListRes.setOwnerId(ownerId);
				buskingListRes.setBusking_genre(genre);
				
				ret.add(buskingListRes);
			}
		}

		return ret;
	}

	@Override
	public List<BuskingListRes> random() {
		// TODO Auto-generated method stub

		List<Busking> list = buskingRespository.findAll();
		List<BuskingListRes> now = new ArrayList<>();
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
				input.setMax_viewers(b.getMax_viewers());
				input.setId(b.getId());

				String genrename = buskingGenreRespository.getOne(b.getBusking_genre()).getName();
				input.setBusking_genre(genrename);
				String ownerID = userRepository.getOne(b.getOwner_id()).getUserId();
				input.setOwnerId(ownerID);
				
				now.add(input);
			}
		}

		int size = Math.min(6, now.size());
		int[] numbers = new int[size];
		
		for(int i = 0; i < numbers.length ; i++){
			numbers[i] = (int)(Math.random() * now.size());
			for(int searchCur = 0; searchCur < i; searchCur ++){
				if(numbers[i] == numbers[searchCur]){
					i--;
					break;
				}
			}
		}
		
		
		for(int i=0; i<numbers.length; i++) {
//			System.out.println(numbers[i]);
			ret.add(now.get(numbers[i]));
		}
		
		
		return ret;
	}


}
