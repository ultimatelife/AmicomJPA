package com.amicom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("compiler")
public class CompilerController {
	
	@RequestMapping("java")
	String java(){
		return "compiler/java";
	}
	
	@RequestMapping("c_language")
	String c_language(){
		return "compiler/c_language";
	}
	
}
