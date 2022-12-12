package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// super.configure(http);
		http.authorizeHttpRequests().mvcMatchers("/","/all/**","/join").permitAll()
		.mvcMatchers("/admin/**").hasRole("admin")	// role이 admin이여야 접속가능
		.anyRequest().authenticated();
		
		http.formLogin().loginPage("/login").permitAll()
		.defaultSuccessUrl("/service1");	// 로그인하면 서비스1화면으로 보낸다
		
		// 로그아웃은 시큐리티에서 알아서 해준다
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/login");
		
		http.httpBasic();
	}
}
