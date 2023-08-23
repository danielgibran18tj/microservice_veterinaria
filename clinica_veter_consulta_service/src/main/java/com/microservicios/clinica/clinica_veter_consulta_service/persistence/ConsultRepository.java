package com.microservicios.clinica.clinica_veter_consulta_service.persistence;

import com.microservicios.clinica.clinica_veter_consulta_service.domain.model.Consult;
import com.microservicios.clinica.clinica_veter_consulta_service.persistence.entity.ConsultEntity;
import com.microservicios.clinica.clinica_veter_consulta_service.persistence.entity.IdConsultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRepository extends JpaRepository<ConsultEntity, IdConsultEntity> {
    @Modifying
    @Query("DELETE FROM ConsultEntity as c WHERE c.idEmpleado = :idEmpleado AND c.idMascota = :idMascota")
    void deleteByConsultId(@Param("idMascota") Integer idMascota, @Param("idEmpleado") Integer idEmpleado);

    //Consult findConsultEntityBy(Integer idMascota, Integer idEmpleado);

    ConsultEntity findByIdMascotaAndIdEmpleado(Integer idMascota, Integer idEmpleado);

    boolean existsByIdMascotaAndIdEmpleado(Integer idMascota, Integer idEmpleado);

}
