package io.javabrains.resumeportal.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "io.javabrains.resumeportal.controller")
@EntityScan(basePackages = "io.javabrains.resumeportal.model")
@EnableJpaRepositories(basePackages = "io.javabrains.resumeportal.repository")
public class ResumePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumePortalApplication.class, args);
	}
}
