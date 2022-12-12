
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

@SpringBootApplication
public class SecurityTestApplication {

	public static void main(String[] args) {
		
		//DBManager.insert(new MemberVO("kim",PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("kim"),"kim","user"));
		//DBManager.insert(new MemberVO("yang",PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("yang"),"yang","admin"));
		
		SpringApplication.run(SecurityTestApplication.class, args);
	}

}
