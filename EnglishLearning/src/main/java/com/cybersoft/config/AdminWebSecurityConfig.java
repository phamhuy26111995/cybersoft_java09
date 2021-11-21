//package com.cybersoft.config;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.cybersoft.common.PasswordEncoderNonEncrypt;
//
//@Configuration
//@EnableWebSecurity
//@ComponentScan("com.cybersoft")
//@Order(1)
//public class AdminWebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	 @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new PasswordEncoderNonEncrypt();
//	    }
//	
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		// TODO Auto-generated method stub
//		return super.authenticationManagerBean();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.antMatcher("/admin/**").authorizeRequests().antMatchers("/admin/login").permitAll();
//		
//		
//
//		http.authorizeRequests().and().formLogin()//
//	    
//        // Submit URL của trang login
//        .loginProcessingUrl("/admin_login") // Submit URL
//        .loginPage("/admin/login")//
//        .defaultSuccessUrl("/admin")//
//       
//        .usernameParameter("j_username")//
//        .passwordParameter("j_password")
//    
//        // Cấu hình cho Logout Page.
//        .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login");
//		
//	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// TODO Auto-generated method stub
//		web.ignoring().antMatchers("/resources/**");
//	}
//	
//	
//}
