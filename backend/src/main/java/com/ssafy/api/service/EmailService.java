package com.ssafy.api.service;

import javax.mail.MessagingException;

public interface EmailService {
	void sendMail(String toEmail, String subject, String msg) throws MessagingException;
}
