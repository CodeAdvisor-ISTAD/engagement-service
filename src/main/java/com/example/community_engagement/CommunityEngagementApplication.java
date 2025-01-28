package com.example.community_engagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommunityEngagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityEngagementApplication.class, args);
	}

}
