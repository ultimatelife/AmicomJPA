package com.amicom.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amicom.controller.form.Login;

@Transactional
public interface LoginRepository extends JpaRepository<Login, String>{
}
