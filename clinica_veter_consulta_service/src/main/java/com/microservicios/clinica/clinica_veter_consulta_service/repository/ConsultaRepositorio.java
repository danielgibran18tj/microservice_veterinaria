package com.microservicios.clinica.clinica_veter_consulta_service.repository;

import com.microservicios.clinica.clinica_veter_consulta_service.entity.Consulta;
import com.microservicios.clinica.clinica_veter_consulta_service.entity.MascotaEmpleadoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ConsultaRepositorio extends CrudRepository<Consulta, MascotaEmpleadoId> {
    @Modifying
    @Query("DELETE FROM Consulta as c WHERE c.idEmpleado = :idEmpleado AND c.idMascota = :idMascota")
    void deleteByCompositeKey(@Param("idEmpleado") Integer idEmpleado, @Param("idMascota") Integer idMascota);

}
