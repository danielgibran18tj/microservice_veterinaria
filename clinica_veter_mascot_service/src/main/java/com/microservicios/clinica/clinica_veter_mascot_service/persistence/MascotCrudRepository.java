package com.microservicios.clinica.clinica_veter_mascot_service.persistence;

import com.microservicios.clinica.clinica_veter_mascot_service.persistence.entity.MascotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotCrudRepository extends JpaRepository<MascotEntity, Integer> {
}
