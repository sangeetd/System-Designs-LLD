package com.sangeet.logisticsystem.project;

import com.sangeet.logisticsystem.project.model.Vehicle;
import com.sangeet.logisticsystem.project.repo.OrderRepo;
import com.sangeet.logisticsystem.project.repo.UserRepo;
import com.sangeet.logisticsystem.project.repo.VehicleOrderRepo;
import com.sangeet.logisticsystem.project.repo.VehicleRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LogisticSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticSystemApplication.class, args);
	}

	@Bean
	public OrderRepo orderRepoBean(){
		return new OrderRepo();
	}

	@Bean
	public UserRepo userRepoBean(){
		return new UserRepo();
	}

	@Bean
	public VehicleRepo vehicleRepoBean(){
		return new VehicleRepo();
	}

	@Bean
	public VehicleOrderRepo vehicleOrderRepoBean(){
		return new VehicleOrderRepo();
	}

}
