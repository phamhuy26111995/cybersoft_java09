package com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cybersoft.common.PasswordEnconderTest;
import com.cybersoft.filter.AuthFilter;


@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	public AdminSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	
	 @Bean 
	 public PasswordEncoder passwordEncoder() { 
		 return new BCryptPasswordEncoder();
		 }
	 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable() // TẮT CHỨC NĂNG CHỐNG TẤN CÔNG GIẢ MẠO REQUEST
		.antMatcher("/api/admin/**") 
		.authorizeRequests()
		.antMatchers("/api/admin/auth/login")
		.permitAll()
		.antMatchers("/api/admin/role/**")
		.hasAnyRole("ADMIN")
		.antMatchers("/api/admin/user/**")
		.hasAnyRole("ADMIN", "TEACHER")
		.anyRequest()
		.authenticated();
		
		http.addFilter(new AuthFilter(authenticationManager(), userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/v2/api-docs",
		"/configuration/ui",
		"/swagger-resources/**",
		"/configuration/security",
		"/swagger-ui.html",
		"/webjars/**");
	}
	
	
	
}
