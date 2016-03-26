package com.amicom.service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amicom.dao.AmicomMember;
import com.amicom.repository.AmicomMemberRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService {
	@Autowired
	AmicomMemberRepository amicomMemberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AmicomMember user = amicomMemberRepository.findByUsername(username);

		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		if (username.equals("admin")) {
			auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		}
		
		String password = user.getPassword();
		return new LoginUserDetails(user);
	}
}
