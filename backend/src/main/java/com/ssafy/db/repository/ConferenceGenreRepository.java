package com.ssafy.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Conference_genre;

@Repository
public interface ConferenceGenreRepository extends JpaRepository<Conference_genre, Long>{

//	Conference_genre findGenreByGenreName(String name);
	
}
