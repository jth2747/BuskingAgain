package com.ssafy.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Fav_genre;
import com.ssafy.db.entity.QFav_genre;

@Repository
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
