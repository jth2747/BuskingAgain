package com.ssafy.api.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.service.EmailService;
import com.ssafy.db.entity.User;

import lombok.RequiredArgsConstructor;

/**
 * mail 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/send")
	public void sendmail(User user) throws MessagingException {
		
	}
}
