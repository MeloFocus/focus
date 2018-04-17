package com.melo.focus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	//@RequiresPermissions(value="how_are_you")
	public String hello(){
		
		return "HelloWorld";
	}
}
