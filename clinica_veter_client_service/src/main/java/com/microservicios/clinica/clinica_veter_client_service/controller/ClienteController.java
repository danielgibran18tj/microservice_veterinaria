package com.microservicios.clinica.clinica_veter_client_service.controller;

import com.microservicios.clinica.clinica_veter_client_service.controller.ifc.ClienteAPI;
import com.microservicios.clinica.clinica_veter_client_service.entity.Cliente;
import com.microservicios.clinica.clinica_veter_client_service.service.ClienteServicio;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController implements ClienteAPI {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    private final HttpServletRequest request;

    private final ClienteServicio clienteServicio;

    public ClienteController(HttpServletRequest request, ClienteServicio clienteServicio) {
        this.request = request;
        this.clienteServicio = clienteServicio;
    }


    public ResponseEntity<List<Cliente>> getAll(){
        try {
            return new ResponseEntity<List<Cliente>>(clienteServicio.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<Cliente>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Cliente> getId(@ApiParam(value = "ID of Product to return",required=true) @PathVariable("idClient") Integer idCliente) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Cliente cliente = clienteServicio.getId(idCliente);
                if (cliente != null) {
                    return new ResponseEntity<>(cliente, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Cliente no encontrado
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Cliente>(HttpStatus.NOT_IMPLEMENTED);
    }

    //QUERY PERSONALIZADO, CONSULTA CON DATOS DE CLIENTE
    //@GetMapping("/summary/{id}")
    public ResponseEntity<List<Object[]>> getSummary(@PathVariable Integer id){        //revisar codigo
        List<Object[]> consultas = clienteServicio.getConsultaMascota(id);
        if (consultas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(consultas);
        }
    }


    public ResponseEntity<Cliente> agg(@RequestBody Cliente cliente){
        Cliente cliente1 = clienteServicio.save(cliente);
        if (cliente1 != null){
            return ResponseEntity.ok(cliente1);
        }
        return ResponseEntity.badRequest().build();
    }


    public ResponseEntity<Cliente> update(@RequestBody Cliente cliente){
        Cliente cliente1 = clienteServicio.update(cliente);
        if (cliente1 != null){
            return ResponseEntity.ok(cliente1);
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<Void> delete(@ApiParam(value = "ID of Product to return",required=true) @PathVariable("idClient") int idClient){
        Boolean cliente = clienteServicio.delete(idClient);
        if (cliente == true){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
