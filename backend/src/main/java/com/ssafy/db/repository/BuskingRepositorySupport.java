package com.ssafy.db.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Busking;
import com.ssafy.db.entity.QBusking;

@Repository
public class BuskingRepositorySupport {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QBusking qBusking = QBusking.busking;

	public List<Busking> findBuskingByOwnerId(Long owner_id) {
		List<Busking> busking = jpaQueryFactory.select(qBusking).from(qBusking).where(qBusking.owner_id.eq(owner_id)).fetch();
		if(busking == null)
			return null;
		return busking;
	}
}
