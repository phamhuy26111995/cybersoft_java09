package com.cybersoft.dto;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
@Getter
@Setter
public class UserDetailDto extends User implements UserDetails {
	private String fullname;
	private String avatar;
	private Long id;
	private int roleId;
	public UserDetailDto(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String fullname, String avatar) {
		super(username, password, authorities);
		this.fullname = fullname;
		this.avatar = avatar;
	}
	public UserDetailDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public UserDetailDto(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id) {
		super(username, password, authorities);
		this.id = id;
	}
	public UserDetailDto(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id,
			int roleId) {
		super(username, password, authorities);
		this.id = id;
		this.roleId = roleId;
	}
	
	
	
}
