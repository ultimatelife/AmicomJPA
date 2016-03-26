package com.amicom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amicom.repository.ImageReplyRepository;

@Service
public class ImageReplyService {
	@Autowired
	ImageReplyRepository imageReplyRepository;
}
