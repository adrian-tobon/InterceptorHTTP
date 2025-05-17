package com.curso.springboot.interceptorhttp.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
