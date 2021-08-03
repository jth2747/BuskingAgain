package com.ssafy.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Liked;
import com.ssafy.db.entity.QLiked;

@Repository
public class LikedRepositorySupport {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QLiked qliked = QLiked.liked;
	
	public Liked findLikedByUid(Long u_id, Long b_id) {
		Liked liked = jpaQueryFactory.select(qliked).from(qliked).where(qliked.u_id.eq(u_id).and(qliked.b_id.eq(b_id))).fetchOne();
		if(liked == null) return null;
		return liked;
	}
}
