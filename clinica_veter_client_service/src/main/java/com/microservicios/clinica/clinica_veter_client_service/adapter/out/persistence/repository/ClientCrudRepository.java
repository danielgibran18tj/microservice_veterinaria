package com.microservicios.clinica.clinica_veter_client_service.adapter.out.persistence.repository;

import com.microservicios.clinica.clinica_veter_client_service.adapter.out.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCrudRepository extends JpaRepository<ClientEntity, Integer> {

    /*@Query(value =
            "SELECT     cliente.id_cliente AS id_cliente,       " +
            "           cliente.nombre AS duenio_mascota,       " +
            "           mascota.nombre AS nombre_mascota,       " +
            "           mascota.id_mascota AS id_mascota,       " +
            "           empleado.nombre AS nombre_doctor,       " +
            "           consulta.fecha AS horario_consulta      " +
            "FROM       clinica_veterinaria.empleado empleado   " +
            " inner join                                        " +
            "           clinica_veterinaria.consulta consulta ON empleado.id_empleado = consulta.id_empleado    " +
            " inner join                                        " +
            "           clinica_veterinaria.mascota mascota ON consulta.id_mascota = mascota.id_mascota         " +
            " inner join                                        " +
            "           clinica_veterinaria.cliente cliente ON mascota.id_cliente = cliente.id_cliente          " +
            " where                                             " +
            " cliente.id_cliente = :clienteId                   " +
            " GROUP BY  cliente.id_cliente," +
            "           cliente.nombre," +
            "           mascota.nombre," +
            "           mascota.id_mascota," +
            "           empleado.nombre," +
            "           consulta.fecha", nativeQuery = true)
    ClienteSummary datosConsultaMascota(@Param("clienteId") Integer clienteId);*/


}
