package com.microservicios.clinica.clinica_veter_consulta_service.controller;

import com.microservicios.clinica.clinica_veter_consulta_service.service.ConsultaServicio;
import com.microservicios.clinica.clinica_veter_consulta_service.entity.Consulta;
import com.microservicios.clinica.clinica_veter_consulta_service.entity.MascotaEmpleadoId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {
    private final ConsultaServicio consultaServicio;

    @Autowired
    public ConsultaController(ConsultaServicio consultaServicio) {
        this.consultaServicio = consultaServicio;
    }

    @Operation(summary = "Obtener lista de consultas")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista disponible"),
            @ApiResponse(responseCode = "404", description = "Lista no encontrada")
    })
    public ResponseEntity<List<Consulta>> getAll(){
        return new ResponseEntity<>(consultaServicio.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Crear una nueva consulta")
    @PostMapping
    public ResponseEntity<Consulta> agg(@RequestBody Consulta consulta){
        if (consulta.getIdMascota()!=null && consulta.getIdEmpleado()!=null) {
            MascotaEmpleadoId id = new MascotaEmpleadoId(consulta.getIdMascota(), consulta.getIdEmpleado());
            if (!consultaServicio.exists(id)) {
                return ResponseEntity.ok(this.consultaServicio.save(consulta));
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar consulta")
    @PutMapping
    public ResponseEntity<Consulta> update(Consulta consulta){
        if (consulta.getIdMascota()!=null && consulta.getIdEmpleado()!=null) {
            MascotaEmpleadoId id = new MascotaEmpleadoId(consulta.getIdMascota(), consulta.getIdEmpleado());
            if (consultaServicio.exists(id)) {
                return ResponseEntity.ok(this.consultaServicio.save(consulta));
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Eliminar una consulta por ID")
    @DeleteMapping("/{idMascota}/{idEmpleado}")
    public ResponseEntity<Void> deleteConsultaById(@PathVariable Integer idMascota, @PathVariable Integer idEmpleado) {
        consultaServicio.deleteConsultaById(idEmpleado, idMascota);
        return ResponseEntity.noContent().build();
    }
}
