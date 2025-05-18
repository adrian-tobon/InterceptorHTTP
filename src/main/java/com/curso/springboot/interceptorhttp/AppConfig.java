package com.curso.springboot.interceptorhttp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Autowired()
	@Qualifier("loadingTimeInterceptor")
	private HandlerInterceptor loadingTimeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(loadingTimeInterceptor).addPathPatterns("/app/int");
		registry.addInterceptor(loadingTimeInterceptor).excludePathPatterns("/app/bar","/app/baz");
	}

	
	
}
