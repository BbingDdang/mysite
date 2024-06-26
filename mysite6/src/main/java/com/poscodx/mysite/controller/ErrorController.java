package com.poscodx.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@GetMapping("/404")
	public String _404() {
		return "errors/404";
	}
	
	@GetMapping("/400")
	public String _400() {
		return "errors/4oo";
	}
	
	@GetMapping("/500")
	public String _500() {
		return "errors/500";
	}
	
}
