package com.microservicios.clinica.clinica_veter_client_service.controller;

import com.microservicios.clinica.clinica_veter_client_service.entity.Cliente;
import com.microservicios.clinica.clinica_veter_client_service.entity.Mascota;
import com.microservicios.clinica.clinica_veter_client_service.repository.projection.ClienteSummary;
import com.microservicios.clinica.clinica_veter_client_service.service.ClienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    private final ClienteServicio clienteServicio;

    @Autowired
    public ClienteController(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @Operation(summary = "Ver lista de todos los clientes")
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll(){
        return ResponseEntity.ok((List<Cliente>) this.clienteServicio.getAll());
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> get(@PathVariable int idCliente){
        if (this.clienteServicio.exists(idCliente)){
            return ResponseEntity.ok(this.clienteServicio.getId(idCliente));
        }
        return ResponseEntity.badRequest().build();
    }

    //QUERY PERSONALIZADO, CONSULTA CON DATOS DE CLIENTE
    @GetMapping("/summary/{id}")
    public ResponseEntity<List<Object[]>> getSummary(@PathVariable Integer id){        //revisar codigo
        List<Object[]> consultas = clienteServicio.getConsultaMascota(id);
        if (consultas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(consultas);
        }
    }

    @Operation(summary = "Ingresar un nuevo cliente")
    @PostMapping
    public ResponseEntity<Cliente> agg(@RequestBody Cliente cliente){
        if (cliente.getIdCliente()== null || !this.clienteServicio.exists(cliente.getIdCliente())){
            for (Mascota mascota1 : cliente.getIdMascota()){
                mascota1.setCliente(cliente);
            }
            return ResponseEntity.ok(this.clienteServicio.save(cliente));
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Actualizar un cliente")
    @PutMapping
    public ResponseEntity<Cliente> update(@RequestBody Cliente cliente){
        if (cliente.getIdCliente()!= null && this.clienteServicio.exists(cliente.getIdCliente())){
            for (Mascota mascota1 : cliente.getIdMascota()){
                mascota1.setCliente(cliente);
            }
            return ResponseEntity.ok(this.clienteServicio.save(cliente));
        }
        return ResponseEntity.badRequest().build();    }

    @Operation(summary = "Eliminar un cliente por ID")
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> delete(@PathVariable int idCliente){
        if (this.clienteServicio.exists(idCliente)){
            this.clienteServicio.delete(idCliente);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
