package com.microservicios.clinica.clinica_veter_consulta_service.repository;

import com.microservicios.clinica.clinica_veter_consulta_service.entity.Consulta;
import com.microservicios.clinica.clinica_veter_consulta_service.entity.MascotaEmpleadoId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface ConsultaRepositorio extends ListCrudRepository<Consulta, MascotaEmpleadoId> {
    @Modifying
    @Query("DELETE FROM Consulta as c WHERE c.idEmpleado = :idEmpleado AND c.idMascota = :idMascota")
    void deleteByCompositeKey(@Param("idEmpleado") Integer idEmpleado, @Param("idMascota") Integer idMascota);

}
