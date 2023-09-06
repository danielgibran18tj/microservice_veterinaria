package com.microservicios.clinica.clinica_veter_mascot_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClinicaVeterMascotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaVeterMascotServiceApplication.class, args);
	}

}
