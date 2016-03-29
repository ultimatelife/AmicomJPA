package com.amicom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amicom.dao.ImageBoard;
import com.amicom.dao.ImageReply;
import com.amicom.repository.ImageReplyRepository;

@Service
public class ImageReplyService {
	@Autowired
	ImageReplyRepository imageReplyRepository;

	public List<ImageReply> listReply(int boardId){
		return imageReplyRepository.findByBoard(new ImageBoard(boardId));
	}
	
	public void inset(ImageReply imageReply){
		imageReplyRepository.save(imageReply);
	}
	
	public void delete(int replyId){
		imageReplyRepository.deleteByReplyId(replyId);
	}

	public List<ImageReply> insert(ImageReply imageReply) {
		imageReplyRepository.saveAndFlush(imageReply);
		return imageReplyRepository.findByBoard(imageReply.getBoard());
	}
}
