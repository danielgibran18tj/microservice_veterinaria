package com.microservicios.clinica_veter_employee_service.persistence;

import com.microservicios.clinica_veter_employee_service.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCrudRepository extends JpaRepository<EmployeeEntity, Integer> {
}
