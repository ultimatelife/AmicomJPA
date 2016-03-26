package com.amicom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amicom.controller.form.Login;
import com.amicom.dao.AmicomMember;
import com.amicom.service.AmicomMemberService;
import com.amicom.service.security.LoginUserDetails;

@Controller
@RequestMapping("amicommember")
@SessionAttributes("userInfo")
public class AmicomMemberController {

	@Autowired
	AmicomMemberService amicomMemberService;
	
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
		amicomMember.setEnabled(true);
		amicomMemberService.insert(amicomMember);
		return "redirect:loginForm";
	}

	@ResponseBody
	@RequestMapping(value = "isDuplicate", method = RequestMethod.GET)
	boolean isDulplicate(@RequestBody @RequestParam("username") String username) {
		System.out.println(username);
		return amicomMemberService.isDuplidate(username);
	}

}
