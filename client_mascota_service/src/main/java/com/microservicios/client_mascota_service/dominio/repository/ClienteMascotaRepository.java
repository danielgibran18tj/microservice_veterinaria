package com.microservicios.client_mascota_service.dominio.repository;

import com.microservicios.client_mascota_service.dominio.Client_Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteMascotaRepository extends JpaRepository<Client_Mascota, Integer> {
}
