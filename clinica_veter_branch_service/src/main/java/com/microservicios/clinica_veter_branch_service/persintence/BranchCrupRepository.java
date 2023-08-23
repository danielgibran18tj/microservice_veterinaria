package com.microservicios.clinica_veter_branch_service.persintence;

import com.microservicios.clinica_veter_branch_service.persintence.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchCrupRepository extends JpaRepository<BranchEntity, Integer> {
}
