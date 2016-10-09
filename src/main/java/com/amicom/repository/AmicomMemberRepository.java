package com.amicom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amicom.dao.AmicomMember;

@Repository
@Transactional
public interface AmicomMemberRepository extends JpaRepository<AmicomMember, Integer>{
	AmicomMember findByUsername(String username);

	AmicomMember findByUsernameAndPassword(String username, String password);

	@Query("FROM AmicomMember amicomMeber WHERE amicomMeber.studentNumber = ?1 AND amicomMeber.name = ?2")
	AmicomMember findbyStudentNumberAndName(String studentNumber, String name);
	
	AmicomMember findByUsernameAndStudentNumber(String username, String studentNumber);
	
	@Modifying
	@Query("UPDATE AmicomMember amicomMember SET amicomMember.enabled = 1 WHERE amicomMember.uuid = ?1")
	void confirm(String uuid);



}