package com.url.shortner_sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "Model")
public class UrlShortnerSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortnerSbApplication.class, args);
	}

}
