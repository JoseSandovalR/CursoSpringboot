package net.sandoval.apiproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"net.sandoval.commons.models.entity"})
public class ApiProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProductosApplication.class, args);
	}

}
