package com.amicom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amicom.common.OftenData;
import com.amicom.controller.form.BoardForm;
import com.amicom.dao.BoardChart;
import com.amicom.dao.NormalBoard;
import com.amicom.dao.en.BoardType;
import com.amicom.repository.AmicomMemberRepository;
import com.amicom.repository.NormalBoardRepository;

@Service
public class NormalBoardService {
	@Autowired
	NormalBoardRepository normalBoardRepository;

	@Autowired
	NormalFileMetaService normalFileMetaService;
	
	@Autowired
	AmicomMemberRepository amicomMemberRepository;

	public List<NormalBoard> list(String boardName, Pageable pageable) {
		return normalBoardRepository.findByBoardName(new BoardChart(boardName), pageable);
	}

	public NormalBoard read(int boardId) {
		return normalBoardRepository.findOne(boardId);
	}

	public void save(BoardForm normalBoardForm, String username) {
		NormalBoard normalBoard = new NormalBoard();

		normalBoard.setTitle(normalBoardForm.getTitle());
		normalBoard.setContent(normalBoardForm.getContent());
		normalBoard.setBoardName(new BoardChart(normalBoardForm.getBoardName(), BoardType.NormalBoard));
		normalBoard.setAmicomMember(amicomMemberRepository.findByUsername(username));
		if (normalBoardForm.getMultipartFiles() != null)
			normalBoard.setMultipartFiles(normalBoardForm.getMultipartFiles());
		normalBoard.setTimeStamp(OftenData.getCurrentTimestamp());

		normalBoardRepository.save(normalBoard);

		try {
			System.out.println("test : " + normalBoardForm.getMultipartFiles().get(0));
			normalFileMetaService.insert(normalBoard, normalBoardForm.getBoardName(), normalBoard.getMultipartFiles());
		} catch (Exception e) {
			
		}
	}

	public void delete(int boardId) {
		normalBoardRepository.delete(boardId);
//		normalBoardRepository.deleteByNormalBoard(boardId);
	}

	public void put(String boardName, NormalBoard normalBoard) {
	}

	public List<NormalBoard> allList(String boardName) {
//		List<NormalBoard> list = normalBoardRepository.findByBoardName(new BoardChart(boardName));
		List<NormalBoard> list = normalBoardRepository.findAll();
		return list;
	}

	public int size(String boardName) {
		return normalBoardRepository.size(new BoardChart(boardName));
	}
	
}
