package com.curso.springboot.interceptorhttp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySources({
	@PropertySource(value="classpath:config/app.properties", encoding = "UTF-8")	
})
public class AppConfig implements WebMvcConfigurer {

	@Autowired()
	@Qualifier("loadingTimeInterceptor")
	private HandlerInterceptor loadingTimeInterceptor;	
	
	@Autowired
	@Qualifier("scheduleInterceptor")
	private HandlerInterceptor scheduleInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loadingTimeInterceptor).addPathPatterns("/app/int");
		//registry.addInterceptor(loadingTimeInterceptor).excludePathPatterns("/app/bar","/app/baz");
		
		registry.addInterceptor(scheduleInterceptor).addPathPatterns("/app/schedule");
		
	}

	
	
}
