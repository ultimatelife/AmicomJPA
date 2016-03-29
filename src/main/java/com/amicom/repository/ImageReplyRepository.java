package com.amicom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amicom.dao.ImageBoard;
import com.amicom.dao.ImageReply;

@Repository
@Transactional
public interface ImageReplyRepository extends JpaRepository<ImageReply, Integer>{

	List<ImageReply> findByBoard(ImageBoard imageBoard);

	@Modifying
	@Query("delete from ImageReply i where i.replyId = ?1")
	int deleteByReplyId(int replydId);
}