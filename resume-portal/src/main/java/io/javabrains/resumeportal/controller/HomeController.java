package io.javabrains.resumeportal.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	@GetMapping("/")
	public String home() {

		Optional<UserProfile> profileOptional = userProfileRepository.findByUserName("Einstein");
		profileOptional.orElseThrow(() -> new RuntimeException("Not Found"));

		UserProfile profile1 = profileOptional.get();

		Job job1 = new Job();
		job1.setId(1);
		job1.setCompany("Company 1");
		job1.setDesignation("Designation 1");
		job1.setStartDate(LocalDate.of(2020, 1, 1));
		// job1.setEndDate(LocalDate.of(2020, 3, 1));
		job1.setCurrentJob(true);
		job1.getResponsibilities().add("Come up with theory of relativity");
		job1.getResponsibilities().add("Advance Quantum Mechanics");
		job1.getResponsibilities().add("Blow people's mind");

		Job job2 = new Job();
		job2.setId(2);
		job2.setCompany("Company 2");
		job2.setDesignation("Designation 2");
		job2.setStartDate(LocalDate.of(2019, 5, 1));
		job2.setEndDate(LocalDate.of(2019, 12, 31));

		job2.getResponsibilities().add("Come up with theory of relativity");
		job2.getResponsibilities().add("Advance Quantum Mechanics");
		job2.getResponsibilities().add("Blow people's mind");

		profile1.getJobs().clear();
		profile1.getJobs().add(job1);
		profile1.getJobs().add(job2);

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
		userProfileOptional.orElseThrow(() -> new UsernameNotFoundException("Not Found" + userId));
		model.addAttribute("userId", userId);
		UserProfile userProfile = userProfileOptional.get();
		model.addAttribute("userProfile", userProfile);

		System.out.println(userProfile.getJobs());

		return "/profile-templates/" + userProfile.getTheme() + "/index";
	}
}
