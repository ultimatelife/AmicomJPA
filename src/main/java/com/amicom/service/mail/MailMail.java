package com.amicom.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailMail {
	@Autowired
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String from, String[] to, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}

	public void signUpConfirm(String from, String[] to, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("Amicom 이메일 인증");
		message.setText("Amicom에서 보내는 email 인증입니다. \n 아래의 link를 클릭하시면 인증이 처리가 됩니다." + msg);
		mailSender.send(message);
	}
}