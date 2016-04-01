package com.amicom.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amicom.controller.form.EmailSendForm;
import com.amicom.dao.AmicomMember;
import com.amicom.service.AmicomMemberService;
import com.amicom.service.mail.MailMail;
import com.amicom.service.security.LoginUserDetails;

@Controller
@RequestMapping("amicommember")
@SessionAttributes({"userInfo", "userAuthority"})
public class AmicomMemberController {

	@Autowired
	AmicomMemberService amicomMemberService;
	
	@Autowired
	MailMail mailMail;
	
	@RequestMapping(value="loginForm", method = RequestMethod.GET)
	String login(Model model) {
		return "loginForm";
	}

//	@RequestMapping(value="process", method = RequestMethod.POST)
//	String loginProcess(@Validated Login login, Model model, @AuthenticationPrincipal LoginUserDetails loginUserDetails, Session session) {
//		System.out.println(login.getUsername());
//		System.out.println(login.getPassword());
//		amicomMemberService.login(login, model, session);
//		return "redirect:/index";
//	}
	
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	String insert(Model model) {
		return "/amicommember/insert";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	String insert(@Validated AmicomMember amicomMember, BindingResult bindingResult, Model model) {
		//Send Mail
		System.out.println("Senior : " + amicomMember.getSenior());
		
		amicomMember.setEnabled(false);
		amicomMember.setUuid(UUID.randomUUID().toString());
		
		amicomMemberService.insert(amicomMember);
		mailMail.signUpConfirm(amicomMember.getUsername(), amicomMember.getUuid());
		
		return "redirect:loginForm";
	}

	@ResponseBody
	@RequestMapping(value = "isDuplicate", method = RequestMethod.GET)
	boolean isDulplicate(@RequestBody @RequestParam("username") String username) {
		System.out.println(username);
		return amicomMemberService.isDuplidate(username);
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	String board(Model model, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		model.addAttribute("userAuthority", loginUserDetails.getUser().getAuthority());
		return "amicommember/board";
	}
	
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	List<AmicomMember> list() {
		return amicomMemberService.list();
	}
	
	@ResponseBody
	@RequestMapping(value = "confirm/{uuid}", method = RequestMethod.GET)
	String confirm(@PathVariable String uuid) {
		try {
			amicomMemberService.confirm(uuid);
			return "Email Confirmation is successed";
		} catch (Exception e) {
			return "Email Confirmation is failed";
		}
	}
	
	@Async
	@ResponseBody
	@RequestMapping(value = "sendMail", method = RequestMethod.POST)
	String sendMail(@AuthenticationPrincipal LoginUserDetails loginUserDetails, EmailSendForm emailSendForm) {
		amicomMemberService.sendMail(loginUserDetails.getUsername(), emailSendForm);
		return "success";
	}
}
