package com.amicom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amicom.dao.ImageReply;

@Repository
@Transactional
public interface ImageReplyRepository extends JpaRepository<ImageReply, Integer>{
}