package com.jeikode.microservice.two;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class TwoApplication {
	
	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(TwoApplication.class, args);
	}
	
	@RequestMapping("/")
	public String sayHello() {
		return "hello two";
	}
	
	@RequestMapping("/one")
	public String sayHelloOne() {
		return restTemplate.getForObject("http://MICROSERVICEONE/", String.class);
	}
	
	 @Bean
	 @LoadBalanced
	 public RestTemplate restTemplate() {
	  return new RestTemplate();
	 }
}
