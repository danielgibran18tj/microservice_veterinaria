package com.microservicios.clinica.clinica_veter_consulta_service.persistence;

import com.microservicios.clinica.clinica_veter_consulta_service.domain.model.Consult;
import com.microservicios.clinica.clinica_veter_consulta_service.persistence.entity.ConsultEntity;
import com.microservicios.clinica.clinica_veter_consulta_service.persistence.entity.IdConsultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultRepository extends JpaRepository<ConsultEntity, IdConsultEntity> {
    @Modifying
    @Query("DELETE FROM ConsultEntity as c WHERE c.idEmpleado = :idEmpleado AND c.idMascota = :idMascota")
    void deleteByConsultId(@Param("idMascota") Integer idMascota, @Param("idEmpleado") Integer idEmpleado);

    //Consult findConsultEntityBy(Integer idMascota, Integer idEmpleado);

    ConsultEntity findByIdMascotaAndIdEmpleado(Integer idMascota, Integer idEmpleado);

    boolean existsByIdMascotaAndIdEmpleado(Integer idMascota, Integer idEmpleado);

    @Query("SELECT c FROM ConsultEntity c WHERE c.idMascota = :idMascota AND c.idEmpleado = :idEmpleado " +
            "AND c.fecha BETWEEN :startTime AND :endTime")
    List<ConsultEntity> findConflictingConsults(@Param("startTime") LocalDateTime startTime,
                                                @Param("endTime") LocalDateTime endTime,
                                                @Param("idMascota") Integer idMascota,
                                                @Param("idEmpleado") Integer idEmpleado);


    @Query("SELECT c FROM ConsultEntity c WHERE c.idEmpleado = :idEmpleado " +
            "AND c.fecha BETWEEN :startTime AND :endTime")
    List<ConsultEntity> findConflictingConsultsForEmployee(@Param("startTime") LocalDateTime startTime,
                                                           @Param("endTime") LocalDateTime endTime,
                                                           @Param("idEmpleado") Integer idEmpleado);


}

