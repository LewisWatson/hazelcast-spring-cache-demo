package com.github.lewiswatson.hazelcast.spring.cache.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HazelcastSpringCacheDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastSpringCacheDemoApplication.class, args);
	}

}

