package io.javabrains.resumeportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

	@GetMapping("/user")
	public String hello() {
		return "Hello!!";
	}

	@GetMapping("/edit")
	public String edit() {
		return "Edit Page";
	}
	
	
	@GetMapping("/view/{userId}")
	public String view(@PathVariable String userId, Model model) {
		model.addAttribute("userId", userId);
		return "profile-templates/3/index";
	}
}
