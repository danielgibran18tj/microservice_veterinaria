package com.microservicios.clinica.clinica_veter_consulta_service.persistence;

import com.microservicios.clinica.clinica_veter_consulta_service.persistence.entity.CitaFeign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<CitaFeign, Integer> {
}
