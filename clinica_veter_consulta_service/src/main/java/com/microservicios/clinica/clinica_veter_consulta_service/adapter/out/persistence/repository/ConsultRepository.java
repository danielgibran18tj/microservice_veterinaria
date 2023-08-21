package com.microservicios.clinica.clinica_veter_consulta_service.adapter.out.persistence.repository;

import com.microservicios.clinica.clinica_veter_consulta_service.adapter.out.persistence.entity.ConsultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRepository extends JpaRepository<ConsultEntity, Integer> {
    /*@Modifying
    @Query("DELETE FROM ConsultEntity as c WHERE c.idEmpleado = :idEmpleado AND c.idMascota = :idMascota")
    void deleteByCompositeKey(@Param("idEmpleado") Integer idEmpleado, @Param("idMascota") Integer idMascota);*/

}
