package com.cybersoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cybersoft.common.PasswordEncoderNonEncrypt;

@Configuration
@EnableWebSecurity
@ComponentScan("com.cybersoft")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new PasswordEncoderNonEncrypt();
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
		http.csrf().disable()
		.authorizeRequests().antMatchers("/login","/signup").permitAll().
		antMatchers("/vocabulary","/exam").access("hasAnyRole('ROLE_ADMIN')")
		.anyRequest().authenticated()
		.and().exceptionHandling().accessDeniedPage("/login/403");
		
		http.authorizeRequests().and().formLogin()//
	    
        // Submit URL của trang login
        .loginProcessingUrl("/j_spring_security_check") // Submit URL
        .loginPage("/login")//
        .defaultSuccessUrl("/category")//
        .failureUrl("/login/403")//
        .usernameParameter("j_username")//
        .passwordParameter("j_password")
    
        // Cấu hình cho Logout Page.
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/resources/**");
	}
	
	
}
