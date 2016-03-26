package com.amicom.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amicom.dao.BoardChart;
import com.amicom.dao.NormalBoard;

@Repository
@Transactional
public interface NormalBoardRepository extends JpaRepository<NormalBoard, Integer> {
	List<NormalBoard> findByBoardName(BoardChart boardName, Pageable pageable);
	
	List<NormalBoard> findByBoardName(BoardChart boardName);

	@Query("SELECT count(n) FROM NormalBoard n WHERE n.boardName = :boardName")
	int size(@Param("boardName") BoardChart boardName);
	
	@Modifying
	@Query("delete from NormalBoard n where n.boardId = ?1")
	public void deleteByNormalBoard(int boardId);
}