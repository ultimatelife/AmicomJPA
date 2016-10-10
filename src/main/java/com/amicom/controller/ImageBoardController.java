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
import com.amicom.dao.ImageBoard;
import com.amicom.service.ImageBoardService;

@Controller
@RequestMapping("imageboard")
@SessionAttributes("userInfo")
public class ImageBoardController {

	@Autowired
	ImageBoardService imageBoardService;

	@RequestMapping(value = "/board/{boardName}", method = RequestMethod.GET)
	String pageMove(@PathVariable String boardName, Principal principal, Model model) {
		model.addAttribute("userInfo", principal.getName());
		return "imageboard/board";
	}

	@RequestMapping(value = "/size/{boardName}", method = RequestMethod.GET)
	@ResponseBody
	List pageSize(@PathVariable String boardName) {
		return Arrays.asList(imageBoardService.size(boardName));
	}

	@RequestMapping(value = "read/detail/{boardId}", method = RequestMethod.GET)
	@ResponseBody
	List<ImageBoard> get(@PathVariable int boardId) {
		return Arrays.asList(imageBoardService.read(boardId));
	}

	@ResponseBody
	@RequestMapping(value = "allList/{boardName}", method = RequestMethod.GET)
	List<ImageBoard> list(@PathVariable String boardName) {
		System.out.println(boardName);
		List<ImageBoard> list = imageBoardService.allList(boardName);
		Collections.reverse(list);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "page/{boardName}", method = RequestMethod.GET)
	List<ImageBoard> pageList(@PathVariable String boardName, @PageableDefault Pageable pageable) {
		return imageBoardService.list(boardName, pageable);
	}

	@RequestMapping(value = "read/{boardId}/{boardName}", method = RequestMethod.GET)
	String readPageMove(@PathVariable String boardId, @PathVariable String boardName) {
		return "imageboard";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	String post(@Validated BoardForm boardForm, BindingResult bindingResult, Principal principal) {
		System.out.println(boardForm.getBoardName());
		System.out.println(principal.getName());
		imageBoardService.save(boardForm, principal.getName());
		return "redirect:/imageboard/board/" + new URLEncoder().encode(boardForm.getBoardName());
	}

//	@RequestMapping(value = "{boardName}", method = RequestMethod.PUT)
//	String put(@PathVariable String boardName, ImageBoard imageBoard) {
//		imageBoardService.put(boardName, imageBoard);
//		return "";
//	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	String delete(@RequestParam String boardName, @RequestParam int boardId) {
		imageBoardService.delete(boardId);
		return "redirect:/imageboard/board/" + new URLEncoder().encode(boardName);
	}
}
