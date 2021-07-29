package com.ssafy.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QUser_busking;
import com.ssafy.db.entity.User_busking;

@Repository
public class UserBuskingRepositorySupport {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QUser_busking quser_busking = QUser_busking.user_busking;
	
	public User_busking findUser_buskingByUid(Long u_id, Long b_id) {
		User_busking user_busking = jpaQueryFactory.select(quser_busking).from(quser_busking).where(quser_busking.u_id.eq(u_id).and(quser_busking.b_id.eq(b_id))).fetchOne();
		return user_busking;
	}
}
