package com.amicom.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.catalina.util.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amicom.controller.form.BoardForm;
import com.amicom.dao.NormalBoard;
import com.amicom.service.NormalBoardService;

@Controller
@RequestMapping("normalboard")
@SessionAttributes("userInfo")
public class NormalBoardController {

	@Autowired
	NormalBoardService normalBoardService;

	@RequestMapping(value = "/board/{boardName}", method = RequestMethod.GET)
	String pageMove(@PathVariable String boardName, Model model, Principal principal) {
		model.addAttribute("userInfo", principal.getName());
		return "normalboard/board";
	}

	@ResponseBody
	@RequestMapping(value = "size/{boardName}", method = RequestMethod.GET)
	int pageSize(@PathVariable String boardName) {
		return normalBoardService.size(boardName);
	}

	@ResponseBody
	@RequestMapping(value = "read/detail/{boardId}", method = RequestMethod.GET)
	List<NormalBoard> get(@PathVariable int boardId) {
		return Arrays.asList(normalBoardService.read(boardId));
	}

	@ResponseBody
	@RequestMapping(value = "allList/{boardName}", method = RequestMethod.GET)
	List<NormalBoard> list(@PathVariable String boardName) {
		List<NormalBoard> list = normalBoardService.allList(boardName);
		Collections.reverse(list);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "page/{boardName}", method = RequestMethod.GET)
	List<NormalBoard> pageList(@PathVariable String boardName, @PageableDefault Pageable pageable) {
		return normalBoardService.list(boardName, pageable);
	}

	@RequestMapping(value = "read/{boardId}/{boardName}", method = RequestMethod.GET)
	String readPageMove(@PathVariable String boardId, @PathVariable String boardName) {
		return "normalboard/normalboardRead";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	String post(@Validated BoardForm normalBoardForm, BindingResult bindingResult, Principal principal) {
		System.out.println(normalBoardForm.getBoardName());
		System.out.println(principal.getName());
		normalBoardService.save(normalBoardForm, principal.getName());
		return "redirect:/normalboard/board/" + new URLEncoder().encode(normalBoardForm.getBoardName());
	}

	@RequestMapping(value = "{boardName}", method = RequestMethod.PUT)
	String put(@PathVariable String boardName, NormalBoard normalBoard) {
		normalBoardService.put(boardName, normalBoard);
		return "";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	String delete(@RequestParam String boardName, @RequestParam int boardId) {
		normalBoardService.delete(boardId);
		return "redirect:/normalboard/board/";
	}
}
