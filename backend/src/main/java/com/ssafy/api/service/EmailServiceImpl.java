package com.ssafy.api.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Override
	public void sendMail(String toEmail, String subject, String msg) throws MessagingException {

		JavaMailSender javaMailSender;
		
		
	}

}
