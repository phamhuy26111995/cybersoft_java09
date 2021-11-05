package com.cybersoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cybersoft.dto.UserDetailsDto;
import com.cybersoft.entity.User;
import com.cybersoft.repository.RoleRepository;
import com.cybersoft.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private RoleRepository roleRepository;
	private UserRepository userRepository;
	public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if(user == null) throw new UsernameNotFoundException("Không tìm thấy tài khoản");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		String roleName = roleRepository.findById(user.getRole_id()).getName();
		authorities.add(new SimpleGrantedAuthority(roleName));
		return new UserDetailsDto(user.getUsername(), user.getPassword(), authorities);
	}

}
