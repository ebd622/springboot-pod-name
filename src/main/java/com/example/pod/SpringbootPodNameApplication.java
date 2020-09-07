package com.example.pod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringbootPodNameApplication {

	@RequestMapping("/pod-name")
	String home() {
		String hostName = System.getenv("HOSTNAME");
		StringBuffer b = new StringBuffer("Request comes to POD: [").append(hostName).append("]");
		return b.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPodNameApplication.class, args);
	}

}
