package com.amicom.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amicom.dao.AmicomMember;
import com.amicom.service.security.LoginUserDetails;

@Controller
@RequestMapping
@SessionAttributes("userInfo")
public class IndexController {
	@RequestMapping("index")
	String index(Principal principal, Model model, @AuthenticationPrincipal LoginUserDetails loginUserDetails){
//		if(loginUserDetails.getUser().getUuid() == null){
//			return "reditect:/amicommember/logout";
//		}
		
		System.out.println(principal.getName());
		model.addAttribute("userInfo", principal.getName());
		return "index";
	}
}
