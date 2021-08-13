package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QUser;
import com.ssafy.db.entity.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class UserRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;

    public Optional<User> findUserByUserId(String userId) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userId.eq(userId)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }

    
    public Optional<User> findUserByUserNamePassword(String name,String email) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.name.eq(name).and(qUser.email.eq(email))).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }

    public Optional<User> findUserPasswordByUserNamePassword(String name,String email) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.name.eq(name).and(qUser.email.eq(email))).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }
    
	public String findUserIdById(Long id) {
		// TODO Auto-generated method stub
		User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.id.eq(id)).fetchOne();
		
		return user.getUserId();
	}

	@Transactional
	public Long modifyUserPW(String userId, String modifyPW) {
		
		System.out.println(modifyPW);

		Long affectedRow = jpaQueryFactory.update(qUser)
				.where(qUser.userId.eq(userId))
				.set(qUser.password, modifyPW)
				.execute();
		
		System.out.println("affectedRow : " + affectedRow);
		
		return affectedRow;
	}

   
}
