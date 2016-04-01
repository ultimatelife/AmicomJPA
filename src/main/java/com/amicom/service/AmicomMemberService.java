package com.amicom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.amicom.common.OftenData;
import com.amicom.controller.form.EmailSendForm;
import com.amicom.controller.form.Login;
import com.amicom.dao.AmicomMember;
import com.amicom.repository.AmicomMemberRepository;
import com.amicom.service.mail.MailMail;
import com.google.common.base.Optional;

@Service
public class AmicomMemberService {
	@Autowired
	AmicomMemberRepository amicomMemberRepository;
	
	@Autowired
	MailMail mailMail;

	public boolean insert(AmicomMember amicomMember) {
		if (!isDuplidate(amicomMember.getUsername())) {
			amicomMember.setTimeStamp(OftenData.getCurrentTimestamp());
			amicomMember.setAuthority("ROLE_USER");
			amicomMemberRepository.save(amicomMember);
			return true;
		}
		return false;
	}

	public boolean isDuplidate(String username) {
		AmicomMember amicomMember = amicomMemberRepository.findByUsername(username);
		try {
			Optional<AmicomMember> optional = Optional.of(amicomMember);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public AmicomMember login(Login login, Model model, Session session) {
		AmicomMember amicomMember = amicomMemberRepository.findByUsernameAndPassword(login.getUsername(),
				login.getPassword());
		try {
			System.out.println(amicomMember.getUsername());
			System.out.println(amicomMember.getName());
			
			Optional<AmicomMember> optional = Optional.of(amicomMember);
			amicomMember.setPassword("");
			session.setAttribute("userInfo", amicomMember);
			return amicomMember;
		} catch (Exception e) {
			return amicomMember;
		}
	}

	public List<AmicomMember> list() {
		return amicomMemberRepository.findAll();
	}

	public void confirm(String uuid) {
		amicomMemberRepository.confirm(uuid);
	}
	
	
	public void sendMail(String username, EmailSendForm emailSendForm) {
		String[] tos = emailSendForm.getTo().stream().toArray(String[]::new);
		mailMail.sendMail(username, tos, emailSendForm.getSubject(), emailSendForm.getContent());
	}
}
