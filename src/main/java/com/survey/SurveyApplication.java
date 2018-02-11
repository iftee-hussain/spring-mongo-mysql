package com.survey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoOperations;

import com.configuration.SpringMongoConfig;

@SpringBootApplication
@EnableMongoAuditing
public class SurveyApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SurveyApplication.class, args);
	}
}
