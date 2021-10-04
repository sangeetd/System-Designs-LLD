package com.sangeetdas.project;

import com.sangeetdas.project.serverregistry.ServerRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.sangeetdas.project.serverregistry.ServerRegistry.getServerInstance;

@SpringBootApplication
public class ChatAppLldApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatAppLldApplication.class, args);
	}

	@Bean
	public static ServerRegistry getServerRegistry(){
		return getServerInstance();
	}


}
