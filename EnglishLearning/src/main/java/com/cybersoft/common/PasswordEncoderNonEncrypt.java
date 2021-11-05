package com.cybersoft.common;
import org.springframework.security.crypto.password.PasswordEncoder;
public class PasswordEncoderNonEncrypt implements PasswordEncoder {

	public String encode(CharSequence rawPassword) {
		  return rawPassword.toString();
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		 return rawPassword.toString().equals(encodedPassword);
	}
	 
}
