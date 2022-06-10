package org.wildhamsters.loginservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import redis.clients.jedis.Jedis;

@SpringBootApplication
public class LoginserviceApplication {
	// public static Jedis JEDIS = new Jedis();
	public static Jedis JEDIS = new Jedis("redis", 6379);

	public static void main(String[] args) {
		SpringApplication.run(LoginserviceApplication.class, args);
	}
}
