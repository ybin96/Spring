package com.example.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

import lombok.Setter;

@Service
@Setter
public class MemberService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("사용자 로그인처리");
		System.out.println("username: "+username);
		
		MemberVO m = DBManager.findById(username);
		if(m == null) {
			throw new UsernameNotFoundException(username);
		}
		
		
		return User.builder()
				.username(username)
				.password(m.getPwd())
				.roles(m.getRole()).build();
	}

}
