package com.curso.springboot.interceptorhttp.controllers;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import java.util.TimeZone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("app")
public class AppController {
	
	
	@GetMapping("/int")
	public Map<String,Object> interceptor(){		
		return Collections.singletonMap("message", "handler  int del controlador");
	}
	
	
	@GetMapping("/bar")
	public Map<String,Object> var(){		
		return Collections.singletonMap("message", "handler bar del controlador");
	}
	
	@GetMapping("/baz")
	public Map<String,Object> baz(){		
		return Collections.singletonMap("message", "handler baz del controlador");
	}
	
	@GetMapping("/schedule")
	public Map<String,Object> schedule(HttpServletRequest request){	
		
		/*SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		isoFormat.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
		Date date = null;
		try {
			date = isoFormat.parse(new Date().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		Map<String, Object> data = new HashMap<>();
		data.put("greeting", request.getAttribute("greeting"));
		data.put("message", "Bienvenidos al sistema de atencion!");
		data.put("time", /*date*/ new Date().toString());
		return data;
	}
}
