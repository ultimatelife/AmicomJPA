package com.amicom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amicom.common.OftenData;
import com.amicom.dao.AmicomMember;
import com.amicom.dao.BoardChart;
import com.amicom.dao.NormalBoard;
import com.amicom.repository.AmicomMemberRepository;
import com.amicom.repository.BoardChartRepository;
import com.amicom.repository.NormalBoardRepository;
import com.amicom.service.mail.MailMail;

@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	BoardChartRepository boardChartRepository;

	@Autowired
	AmicomMemberRepository amicomMemberRepository;

	@Autowired
	NormalBoardRepository normalBoardRepository;

	@Autowired
	MailMail mailMail;
	
	@RequestMapping("normalboard")
	List<NormalBoard> getNormalBoard() {
		// System.out.println("test1");
		BoardChart boardChart = new BoardChart();
		// boardChart.setBoardName("java");
		// boardChart.setType(BoardType.NormalBoard);
		// boardChartRepository.save(boardChart);

		boardChart = boardChartRepository.findAll().get(0);
		System.out.println("test2");
		AmicomMember amicomMember = amicomMemberRepository.findAll().get(0);
		//
		System.out.println("test3");
		NormalBoard normalBoard = new NormalBoard();
		normalBoard.setAmicomMember(amicomMember);
		normalBoard.setBoardName(boardChart);
		normalBoard.setContent("test1");
		normalBoard.setHighlight(true);
		normalBoard.setTimeStamp(OftenData.getCurrentTimestamp());
		normalBoard.setTitle("test1");
		//
		System.out.println("test4");
		normalBoardRepository.save(normalBoard);
		System.out.println("test5");
		//
		NormalBoard temp = new NormalBoard();
		temp.setBoardId(1);
		System.out.println(normalBoard.getBoardId());

		// return Arrays.asList(normalBoardRepository.findOne(3));
		return normalBoardRepository.findAll();
	}

	@RequestMapping("/normalboard/size")
	int normalboardSize() {
		return normalBoardRepository.size(boardChartRepository.findAll().get(0));
	}

	@RequestMapping("amicommember")
	List<AmicomMember> getAmicomMember() {
		return amicomMemberRepository.findAll();
	}

	@RequestMapping("maptest")
	Map<String, List<AmicomMember>> maptest() {
		Map<String, List<AmicomMember>> map = new HashMap<>();
		map.put("AmicomMember", amicomMemberRepository.findAll());
		return map;
	}

	@RequestMapping("mail")
	@ResponseBody
	String testMail() throws MessagingException {
		String[] sts = {"drama0708@gmail.com"};
		
		mailMail.sendMail("amicom@amicom.com",
				sts,
    		   "Testing123", 
    		   "Testing only \n\n Hello Spring Email Sender");
		return "success";
	}
}
