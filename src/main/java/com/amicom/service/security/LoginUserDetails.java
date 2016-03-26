package com.amicom.service.security;

import org.springframework.security.core.authority.AuthorityUtils;

import com.amicom.dao.AmicomMember;

import lombok.Data;

@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
    private final AmicomMember user;

    public LoginUserDetails(AmicomMember user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        System.out.println("test : " + user.getUsername());
        this.user = user;
    }
}
