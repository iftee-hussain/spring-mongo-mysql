package com.survey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing

public class SurveyApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SurveyApplication.class, args);
	}
}
