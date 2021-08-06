package com.ssafy.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.api.request.UserModifyPutReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setUserId(userRegisterInfo.getId());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		if(userRepositorySupport.findUserByUserId(user.getUserId()).isPresent()) {
			System.out.print("중복임!!!!!!!");
			return null;
		}else {			
			user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
			user.setName(userRegisterInfo.getName());
//			user.setGenre(userRegisterInfo.getGenre());
			user.setEmail(userRegisterInfo.getEmail());
			return userRepository.save(user);
		}
	}
	
	@Override
	public boolean checkUserId(String userid) {
		// TODO Auto-generated method stub
		if(userRepositorySupport.findUserByUserId(userid).isPresent()) {
			System.out.println("중복임!!!");
			return false;
		}
		return true;
	}

	@Override
	public User findUserId(String name, String email) {
		//이름이랑 이메일로 아이디 찾기
		if(userRepositorySupport.findUserByUserNamePassword(name, email).isPresent()) {
			User user = userRepositorySupport.findUserByUserNamePassword(name, email).get();
			System.out.println(user.toString());
			return user;
		}
		else {
			return null;
		}
		
	}
	
	@Override
	public User getUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepositorySupport.findUserByUserId(userId).get();
		return user;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}
	
	@Override
	public User modifyUser(UserModifyPutReq userModifyInfo, String userId, Long id) {
		User user = new User();
		User getUser = getUserByUserId(userId);
		System.out.println("회원정보 수정");
		user.setId(id);
		user.setUserId(userId);
		user.setPassword(getUser.getPassword());
		user.setName(userModifyInfo.getName());
		user.setEmail(getUser.getEmail());

		return userRepository.save(user);
	}
	
	@Override
	public User modifyPW(String PW, String userId, Long id) {
		User user = new User();
		User getUser = getUserByUserId(userId);
		System.out.println("비밀번호 수정");
		user.setId(id);
		user.setUserId(userId);
		user.setPassword(passwordEncoder.encode(PW));
		user.setName(getUser.getName());
//		user.setGenre(getUser.getGenre());
		user.setEmail(getUser.getEmail());
		
		return userRepository.save(user);
	}

	@Override
	public String getUserIdById(Long id) {
		// TODO Auto-generated method stub
		
		return userRepositorySupport.findUserIdById(id);
	}



}
