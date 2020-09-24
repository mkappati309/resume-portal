package io.javabrains.resumeportal.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.javabrains.resumeportal.model.Job;
import io.javabrains.resumeportal.model.UserProfile;
import io.javabrains.resumeportal.repository.UserProfileRepository;

@Controller
public class HomeController {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@GetMapping("/user")
	public String hello() {
		UserProfile profile1 = new UserProfile();
		profile1.setId(1);
		profile1.setDesignation("Designation");
		profile1.setUserName("einstein");
		profile1.setFirstName("Einstein");
		profile1.setLastName("Albert");
		profile1.setTheme(1);

		Job job1 = new Job();
		job1.setCompany("Company 1");
		job1.setDesignation("Designation");
		job1.setId(1);
		job1.setStartDate(LocalDate.of(2020, 1, 1));
		job1.setEndDate(LocalDate.of(2020, 3, 1));

		Job job2 = new Job();
		job2.setCompany("Company 2");
		job2.setDesignation("Designation");
		job2.setId(2);
		job2.setStartDate(LocalDate.of(2019, 5, 1));
		job2.setEndDate(LocalDate.of(2020, 1, 1));

		profile1.setJobs(Arrays.asList(job1, job2));

		userProfileRepository.save(profile1);

		return "profile";
	}

	@GetMapping("/edit")
	public String edit() {
		return "Edit Page";
	}

	@GetMapping("/view/{userId}")
	public String view(@PathVariable String userId, Model model) {
		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
		userProfileOptional.orElseThrow(() -> new RuntimeException("Not Found " + userId));

		model.addAttribute("userId", userId);
		UserProfile userProfile = userProfileOptional.get();
		model.addAttribute("userProfile", userProfile);
	
		System.out.println(userProfile.getJobs());
		
		return "profile-templates/" + userProfile.getId() + "/index";
	
	}
}
