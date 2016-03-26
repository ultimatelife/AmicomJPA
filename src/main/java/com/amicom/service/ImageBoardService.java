package com.amicom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amicom.common.OftenData;
import com.amicom.controller.form.BoardForm;
import com.amicom.dao.BoardChart;
import com.amicom.dao.ImageBoard;
import com.amicom.dao.en.BoardType;
import com.amicom.repository.AmicomMemberRepository;
import com.amicom.repository.ImageBoardRepository;

@Service
public class ImageBoardService {
	@Autowired
	ImageBoardRepository imageBoardRepository;

	@Autowired
	ImageFileMetaService imageFileMetaService;
	
	@Autowired
	AmicomMemberRepository amicomMemberRepository;

	public List<ImageBoard> list(String boardName, Pageable pageable) {
		return imageBoardRepository.findByBoardName(new BoardChart(boardName), pageable);
	}

	public ImageBoard read(int boardId) {
		return imageBoardRepository.findOne(boardId);
	}

	public void save(BoardForm boardForm, String username) {
		List<ImageBoard> imageBoards = new ArrayList<>();
		
		for(MultipartFile multipartFile : boardForm.getMultipartFiles()){
			ImageBoard imageBoard = new ImageBoard(
					new BoardChart(boardForm.getBoardName(), BoardType.ImageBoard)
					, boardForm.getTitle()
					, boardForm.getContent()
					, OftenData.getCurrentTimestamp()
					, amicomMemberRepository.findByUsername(username));
		
			imageBoards.add(imageBoard);
		}
		
//		OneToMany
//		imageBoard.setTitle(boardForm.getTitle());
//		imageBoard.setContent(boardForm.getContent());
//		imageBoard.setBoardName(new BoardChart(boardForm.getBoardName(), BoardType.ImageBoard));
//		imageBoard.setAmicomMember(amicomMemberRepository.findByUsername(username));
//		if (boardForm.getMultipartFiles() != null)
//			imageBoard.setMultipartFiles(boardForm.getMultipartFiles());
//		imageBoard.setTimeStamp(OftenData.getCurrentTimestamp());
//		imageBoardRepository.save(imageBoard);
		
		imageBoardRepository.save(imageBoards);

		try {
			System.out.println("test : " + boardForm.getMultipartFiles().get(0).getName());
			imageFileMetaService.insert(imageBoards, boardForm.getMultipartFiles());
		} catch (Exception e) {
			
		}
	}

	public void delete(int boardId) {
		imageBoardRepository.delete(boardId);
	}

	public void put(String boardName, ImageBoard imageBoard) {
	}

	public List<ImageBoard> allList(String boardName) {
		System.out.println(boardName);
		List<ImageBoard> list = imageBoardRepository.findByBoardName(new BoardChart(boardName));
		return list;
	}

	public int size(String boardName) {
		return imageBoardRepository.size(new BoardChart(boardName));
	}
}
