package com.ssafy.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Conference_genre;
import com.ssafy.db.entity.QConference_genre;

@Repository
public class ConferenceGenreRepositorySupport{

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QConference_genre qConference_genre = QConference_genre.conference_genre;
	
	public Conference_genre findGenreByGenreName(String name){
	Conference_genre conference_genre= jpaQueryFactory.select(qConference_genre).from(qConference_genre).where(qConference_genre.name.eq(name)).fetchOne();
	if(conference_genre == null)
		return null;
	return conference_genre;
	}
	

}
