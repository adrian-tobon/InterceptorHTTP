package com.curso.springboot.interceptorhttp.interceptors;

/*import java.util.Date;
import java.util.HashMap;
import java.util.Map;*/
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("loadingTimeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HandlerMethod controllerMethod = (HandlerMethod) handler;
		
		logger.info("LoadingTimeinterceptor: prehandler cargando ....." + controllerMethod.getMethod().getName());
		
		long start= System.currentTimeMillis();
		request.setAttribute("start", start);
		
		Random random = new Random();
		int delay = random.nextInt(500);
		Thread.sleep(delay);
		
		/*Map<String, String> jsonResponse = new HashMap<>();
		jsonResponse.put("error", "No tienes aceso a estas paginas");
		jsonResponse.put("date", new Date().toString());
		
		ObjectMapper mapper = new ObjectMapper();
		String resString = mapper.writeValueAsString(jsonResponse);
		response.setContentType("application/json");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write(resString);
		
		return false;*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
		long end = 	System.currentTimeMillis();
		long start = Long.parseLong(request.getAttribute("start").toString());
		long total = end - start;
		
		logger.info("tiempo transcurrido " + total + " milisegundos");
		logger.info("LoadingTimeinterceptor: posthandler cargando ....." + ((HandlerMethod) handler).getMethod().getName());
		
	}
	
	
	

}
