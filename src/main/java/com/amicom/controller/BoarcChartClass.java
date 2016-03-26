package com.amicom.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amicom.dao.en.BoardType;
import com.amicom.service.BoardChartService;

@Controller
public class BoarcChartClass {

	@Autowired
	BoardChartService boardChartService;

	@ResponseBody
	@RequestMapping(value = "boardlist", method = RequestMethod.GET)
	Map<BoardType, Collection<String>> list() {
		return boardChartService.list();
	}

}
