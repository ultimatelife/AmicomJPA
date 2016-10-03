package com.amicom.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amicom.common.OftenData;
import com.amicom.controller.form.ReplyForm;
import com.amicom.dao.AmicomMember;
import com.amicom.dao.NormalBoard;
import com.amicom.dao.NormalReply;
import com.amicom.service.NormalReplyService;
import com.amicom.service.security.LoginUserDetails;

@Controller
@RequestMapping("reply")
public class NormalReplyController {

	@Autowired
	NormalReplyService normalReplyService;

	@RequestMapping(value = "{boardId}", method = RequestMethod.GET)
	@ResponseBody
	List<NormalReply> replys(@PathVariable String boardId, Principal principal) {
		System.out.println("Reply ID:" + principal.getName());
		System.out.println("Reply TEST BOARD ID :" + boardId);
		return normalReplyService.listReply(Integer.parseInt(boardId));
	}

	@RequestMapping(value = "{boardId}", method = RequestMethod.POST)
	@ResponseBody
	NormalReply save(@PathVariable String boardId, @RequestBody @Validated ReplyForm replyForm, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		AmicomMember amicomMember = loginUserDetails.getUser();
		
		System.out.println(amicomMember.getUsername());
		System.out.println(amicomMember.getName());
		System.out.println(replyForm.getContent());

		NormalReply normalReply = new NormalReply();
		normalReply.setAmicomMember(amicomMember);
		normalReply.setBoard(new NormalBoard(Integer.parseInt(boardId)));
		normalReply.setTimeStamp(OftenData.getCurrentTimestamp());
		normalReply.setContent(replyForm.getContent());
		normalReplyService.insert(normalReply);
		return normalReply;
	}
	
	@RequestMapping(value = "delete/{replyId}", method = RequestMethod.DELETE)
	@ResponseBody
	String delete(@PathVariable String replyId){
		System.out.println("delete replyid : " + replyId);
		normalReplyService.delete(Integer.parseInt(replyId));
		return "success";
	}
}
