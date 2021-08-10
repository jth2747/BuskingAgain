package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Kickout_info;


@Repository
public interface KickoutRepository extends JpaRepository<Kickout_info, Long>{

}
