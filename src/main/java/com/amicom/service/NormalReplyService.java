package com.amicom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amicom.dao.NormalBoard;
import com.amicom.dao.NormalReply;
import com.amicom.repository.NormalReplyRepository;

@Service
public class NormalReplyService {
	@Autowired
	NormalReplyRepository normalReplyRepository;
	
	public List<NormalReply> listReply(int boardId){
		return normalReplyRepository.findByBoard(new NormalBoard(boardId));
	}
	
	public void inset(NormalReply normalReply){
		normalReplyRepository.save(normalReply);
	}
	
	public void delete(int replyId){
		normalReplyRepository.deleteByReplyId(replyId);
	}

	public List<NormalReply> insert(NormalReply normalReply) {
		normalReplyRepository.saveAndFlush(normalReply);
		return normalReplyRepository.findByBoard(normalReply.getBoard());
	}

}