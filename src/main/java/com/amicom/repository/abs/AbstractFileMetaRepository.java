package com.amicom.repository.abs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amicom.dao.abs.AbstractFileMeta;

@Repository
@Transactional
public interface AbstractFileMetaRepository extends JpaRepository<AbstractFileMeta, Integer>{
}
