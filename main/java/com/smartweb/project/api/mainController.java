package com.smartweb.project.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {
	
	@GetMapping("/test")
	public String replyToTEst() {
		return ("So you'v got a token ");
	}

}
