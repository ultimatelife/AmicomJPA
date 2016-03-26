package com.amicom.repository.abs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amicom.dao.abs.AbstractReply;

@Repository
@Transactional
public interface AbstractReplyRepository extends JpaRepository<AbstractReply, Integer>{
}
