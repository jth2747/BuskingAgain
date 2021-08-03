package com.ssafy.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Busking_genre;
import com.ssafy.db.entity.QBusking_genre;

@Repository
public class BuskingGenreRepositorySupport {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QBusking_genre qBusking_genre = QBusking_genre.busking_genre;

	public Busking_genre findGenreByGenreName(String name) {
		Busking_genre busking_genre = jpaQueryFactory.select(qBusking_genre).from(qBusking_genre).where(qBusking_genre.name.eq(name)).fetchOne();
		if(busking_genre == null) return null;
		return busking_genre;
	}
}
