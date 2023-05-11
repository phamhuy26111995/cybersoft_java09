package com.cybersoft.common;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.cybersoft.dto.UserDetailDto;

import java.util.Collection;

//Class trả về id của user đang tương tác với hệ thống
public class IndentifyUser {
	public static Long getIdPrincipal() {
		
		UserDetailDto userDetail = (UserDetailDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetail.getId();
	}

	public static String getRolePrincipal() {
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		return authorities.toString();
	}

	public static String getEmailPrincipal() {
		String email;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			email = ((UserDetails)principal).getUsername();

		}else {
			email = principal.toString();
		}
		return email;
	}
}
