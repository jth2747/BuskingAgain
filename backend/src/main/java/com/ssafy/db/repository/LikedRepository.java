package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Liked;

@Repository
public interface LikedRepository extends JpaRepository<Liked, Long> {

}
