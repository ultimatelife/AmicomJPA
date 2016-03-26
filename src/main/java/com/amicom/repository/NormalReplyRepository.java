package com.amicom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amicom.dao.NormalBoard;
import com.amicom.dao.NormalReply;

@Repository
@Transactional
public interface NormalReplyRepository extends JpaRepository<NormalReply, Integer>{
	public List<NormalReply> findByBoard(NormalBoard normalBoard);
	
	@Modifying
	@Query("delete from NormalReply n where n.replyId = ?1")
	int deleteByReplyId(int replydId);
}