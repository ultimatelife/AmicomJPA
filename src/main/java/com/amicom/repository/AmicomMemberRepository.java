package com.amicom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amicom.dao.AmicomMember;

@Repository
@Transactional
public interface AmicomMemberRepository extends JpaRepository<AmicomMember, Integer>{
	AmicomMember findByUsername(String username);

	AmicomMember findByUsernameAndPassword(String username, String password);
}