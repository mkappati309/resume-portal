package io.javabrains.resumeportal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/user")
	public String hello() {
		return "Hello!!";
	}

	@GetMapping("/edit")
	public String edit() {
		return "Edit Page";
	}
}
 