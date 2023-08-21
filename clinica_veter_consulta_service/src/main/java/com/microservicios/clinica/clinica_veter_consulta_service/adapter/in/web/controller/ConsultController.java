package com.microservicios.clinica.clinica_veter_consulta_service.adapter.in.web.controller;

import com.microservicios.clinica.clinica_veter_consulta_service.domain.Consult;
import com.microservicios.clinica.clinica_veter_consulta_service.service.ConsultService;
import com.microservicios.clinica.clinica_veter_consulta_service.adapter.out.persistence.entity.ConsultEntity;
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
public class ConsultController {
    private final ConsultService consultService;

    @Autowired
    public ConsultController(ConsultService consultService) {
        this.consultService = consultService;
    }

    @Operation(summary = "Obtener lista de consultas")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista disponible"),
            @ApiResponse(responseCode = "404", description = "Lista no encontrada")
    })
    public ResponseEntity<List<Consult>> getAll(){
        return new ResponseEntity<>(consultService.getAll(), HttpStatus.OK);
    }

    /*@Operation(summary = "Crear una nueva consulta")
    @PostMapping
    public ResponseEntity<Consult> agg(@RequestBody Consult consultEntity){
        if (consultEntity.getIdMascota()!=null && consultEntity.getIdEmpleado()!=null) {
            MascotaEmpleadoId id = new MascotaEmpleadoId(consultEntity.getIdMascota(), consultEntity.getIdEmpleado());
            if (!consultService.exists(id)) {
                return ResponseEntity.ok(this.consultService.save(consultEntity));
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar consulta")
    @PutMapping
    public ResponseEntity<ConsultEntity> update(ConsultEntity consultEntity){
        if (consultEntity.getIdMascota()!=null && consultEntity.getIdEmpleado()!=null) {
            MascotaEmpleadoId id = new MascotaEmpleadoId(consultEntity.getIdMascota(), consultEntity.getIdEmpleado());
            if (consultService.exists(id)) {
                return ResponseEntity.ok(this.consultService.save(consultEntity));
            }
        }
        return ResponseEntity.badRequest().build();
    }*/

    /*@Operation(summary = "Eliminar una consulta por ID")
    @DeleteMapping("/{idMascota}/{idEmpleado}")
    public ResponseEntity<Void> deleteConsultaById(@PathVariable Integer idMascota, @PathVariable Integer idEmpleado) {
        consultService.deleteConsultaById(idEmpleado, idMascota);
        return ResponseEntity.noContent().build();
    }*/
}
