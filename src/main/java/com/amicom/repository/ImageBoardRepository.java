package com.amicom.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amicom.dao.BoardChart;
import com.amicom.dao.ImageBoard;

@Repository
@Transactional
public interface ImageBoardRepository extends JpaRepository<ImageBoard, Integer>{

	List<ImageBoard> findByBoardName(BoardChart boardChart, Pageable pageable);
	
	@Query("SELECT count(n) FROM ImageBoard n WHERE n.boardName = :boardName")
	int size(@Param("boardName") BoardChart boardName);

	List<ImageBoard> findByBoardName(BoardChart boardChart);
}