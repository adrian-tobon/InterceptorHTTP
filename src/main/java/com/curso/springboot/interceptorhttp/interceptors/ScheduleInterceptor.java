package com.curso.springboot.interceptorhttp.interceptors;



//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("scheduleInterceptor")
public class ScheduleInterceptor implements HandlerInterceptor{
	
	
	@Value("${config.calendar.open}")
	private Integer open;
	
	@Value("${config.calendar.close}")
	private Integer close;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		
		System.out.println("hora de ingreso " + hour);
		System.out.println("hora de apertura " + open);
		System.out.println("hora de cierre " + close);
		
		if(hour >= open && hour <= close){
			
			StringBuilder message = new StringBuilder("Bienvenidos, el horario de atencion al cliente");
			message.append(" es desde las ");
			message.append(open);
			message.append("hrs hasta las ");
			message.append(close);
			message.append("hrs.");
			request.setAttribute("greeting", message);			
			return true;
		}else {
			
			/*SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			isoFormat.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
			Date date = null;
			try {
				date = isoFormat.parse(new Date().toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}*/
			
			Map<String, String> jsonResponse = new HashMap<>();
			jsonResponse.put("error", "El sitio esta cerrado en este horario");
			jsonResponse.put("date",/* date.toString()*/ new Date().toString());
			
			ObjectMapper mapper = new ObjectMapper();
			String resString = mapper.writeValueAsString(jsonResponse);
			response.setContentType("application/json");
			response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
			response.getWriter().write(resString);
			
			return false;
		}
		
		//return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
	}
	
	

}
