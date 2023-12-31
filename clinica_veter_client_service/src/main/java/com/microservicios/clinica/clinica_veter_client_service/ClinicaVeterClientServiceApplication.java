package com.microservicios.clinica.clinica_veter_client_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClinicaVeterClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaVeterClientServiceApplication.class, args);
	}

}
