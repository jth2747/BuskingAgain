package com.ssafy.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Kickout_info;
import com.ssafy.db.entity.QKickout_info;

@Repository
public class KickoutRepositorySupport {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QKickout_info qkickout_info = QKickout_info.kickout_info;
	

	
	public Kickout_info findUserByUid(Long u_id, Long b_id) {
		Kickout_info kickout_info = jpaQueryFactory.select(qkickout_info).from(qkickout_info).where(qkickout_info.u_id.eq(u_id).and(qkickout_info.b_id.eq(b_id))).fetchOne();
		if(kickout_info == null) return null;
		return kickout_info;
	}

}


/*
 *@Repository
public class FollowRepositorySupport {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QFav_genre qfav_genre = QFav_genre.fav_genre;
	
	public Fav_genre findFavGenreByGid(Long u_id, Long g_id) {
		Fav_genre fav_genre = jpaQueryFactory.select(qfav_genre).from(qfav_genre).where(qfav_genre.u_id.eq(u_id).and(qfav_genre.g_id.eq(g_id))).fetchOne();
		if(fav_genre == null) return null;
		return fav_genre;
	}
}
 */