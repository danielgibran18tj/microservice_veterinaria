package com.microservicios.clinica.clinica_veter_client_service.repository;

import com.microservicios.clinica.clinica_veter_client_service.entity.Cliente;
import com.microservicios.clinica.clinica_veter_client_service.repository.projection.ClienteSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRespositorio extends JpaRepository<Cliente, Integer> {

    @Query(value =
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
    ClienteSummary datosConsultaMascota(@Param("clienteId") Integer clienteId);

    /*@Query("SELECT c.idCliente AS id_cliente, " +
            "c.nombre AS duenio_mascota, " +
            "m.nombre AS nombre_mascota, " +
            "m.idMascota AS id_mascota, " +
            "e.nombre AS nombre_doctor, " +
            "co.fecha AS horario_consulta " +
            "FROM Cliente c " +
            "INNER JOIN c.mascotas m " +
            "INNER JOIN m.consultas co " +
            "INNER JOIN co.empleado e " +
            "WHERE c.idCliente = :idCliente")
    List<Object[]> obtenerDatosConsultaPorClienteId(Integer idCliente);*/
}
