package com.amicom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amicom.dao.BoardChart;
import com.amicom.dao.ImageFileMeta;

@Repository
@Transactional
public interface ImageFileMetaRepository extends JpaRepository<ImageFileMeta, Integer>{
	@Query("SELECT count(n) FROM ImageBoard n WHERE n.boardName = :boardName")
	int size(@Param("boardName") BoardChart boardName);
}