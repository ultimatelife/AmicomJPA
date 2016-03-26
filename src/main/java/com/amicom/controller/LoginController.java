package com.amicom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amicom.controller.form.Login;
import com.amicom.service.AmicomMemberService;
import com.amicom.service.security.LoginUserDetails;

@Controller
//@RequestMapping("amicommember")
@SessionAttributes("userInfo")
public class LoginController {
	@Autowired
	AmicomMemberService amicomMemberService;

//	@RequestMapping(value="loginForm", method = RequestMethod.GET)
//	String login(Model model) {
//		return "loginForm";
//	}
//
//	@RequestMapping(value="process", method = RequestMethod.POST)
//	String loginProcess(@Validated Login login, Model model, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
//		System.out.println(login.getUsername());
//		System.out.println(login.getPassword());
//		amicomMemberService.login(login, model);
//		return "redirect:/index";
//	}
}
