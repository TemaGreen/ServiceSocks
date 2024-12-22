package socks.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Service Socks ",
				version = "1.0",
				description = "This API was created as part of a test task"))
public class ServiceSocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceSocksApplication.class, args);
	}

}
