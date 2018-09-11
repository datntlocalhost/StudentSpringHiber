package com.runsystem.datnt.errorhandles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HttpErrorHandle {
	
	@RequestMapping(value = "/400")
	public String error400() {
		return "error/400";
	}
	
	@RequestMapping(value = "/403")
	public String error403() {
		return "error/403";
	}
	
	@RequestMapping(value = "/404")
	public String error404() {
		return "error/404";
	}
	
	@RequestMapping(value = "/500")
	public String error500() {
		return "error/500";
	}
}
