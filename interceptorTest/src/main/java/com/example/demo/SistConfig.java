package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SistConfig implements WebMvcConfigurer {

	@Autowired
	private MemberInterceptor interceptor;
	
	public void setInterceptor(MemberInterceptor interceptor) {
		this.interceptor = interceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
		.addPathPatterns("/member/**")	// member에 모든 파일에대해
		.excludePathPatterns("/member/images/**"); // member/images에있는 파일은 제외
	}

	
}
