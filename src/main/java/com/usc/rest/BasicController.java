package com.usc.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

	@GetMapping("/")
	public String lcs() {
		return "index";
	}

}