package com.heraizen.ems;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

@Configuration
public class AppConfig {
	
	@Value("${mongo.uri}")
	private String mongoUri;

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(new SimpleMongoClientDbFactory(mongoUri)

		);
	}
	
   
}
