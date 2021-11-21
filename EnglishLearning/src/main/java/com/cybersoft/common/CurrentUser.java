package com.cybersoft.common;

import org.springframework.security.core.context.SecurityContextHolder;

import com.cybersoft.dto.UserDetailsDto;

public class CurrentUser {
	public static UserDetailsDto getPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal != null && principal instanceof UserDetailsDto) {
			UserDetailsDto userDetailsDto = (UserDetailsDto)principal;
			return userDetailsDto;
		}
		return null;
		
		
	}
	
}
