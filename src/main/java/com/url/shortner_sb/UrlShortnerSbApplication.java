package com.url.shortner_sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "Model")
@EnableJpaRepositories(basePackages = "Repository")
public class UrlShortnerSbApplication {
	public static void main(String[] args) {
		SpringApplication.run(UrlShortnerSbApplication.class, args);
	}
}
