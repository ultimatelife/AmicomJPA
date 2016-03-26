package com.amicom.repository.abs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amicom.dao.abs.AbstractBoard;

@Repository
@Transactional
public interface AbstractBoardRepository extends JpaRepository<AbstractBoard, Integer>{
}
