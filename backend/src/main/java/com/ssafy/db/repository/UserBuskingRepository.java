package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.User_busking;

@Repository
public interface UserBuskingRepository extends JpaRepository<User_busking, Long>{

}
