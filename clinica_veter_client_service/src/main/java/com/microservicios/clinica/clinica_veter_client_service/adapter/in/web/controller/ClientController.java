package com.microservicios.clinica.clinica_veter_client_service.adapter.in.web.controller;

import com.microservicios.clinica.clinica_veter_client_service.adapter.in.web.ifc.ClienteAPI;
import com.microservicios.clinica.clinica_veter_client_service.common.exception.ApplicationException;
import com.microservicios.clinica.clinica_veter_client_service.domain.Client;
import com.microservicios.clinica.clinica_veter_client_service.service.ClientServicePort;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClientController implements ClienteAPI {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    private final HttpServletRequest request;

    private final ClientServicePort clientServicePort;

    public ClientController(HttpServletRequest request, ClientServicePort clientServicePort) {
        this.request = request;
        this.clientServicePort = clientServicePort;
    }


    public ResponseEntity<List<Client>> getAll(){
        try {
            return new ResponseEntity<List<Client>>(clientServicePort.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);  //?????
            return new ResponseEntity<List<Client>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Client> getId(@ApiParam(value = "ID of Product to return",required=true) @PathVariable("idClient") Integer idClient) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Client client = clientServicePort.getId(idClient);
                if (client != null) {
                    return new ResponseEntity<>(client, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Cliente no encontrado
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Client> agg(@RequestBody Client client){
        Client cliente1 = clientServicePort.save(client);
        try {
            if (cliente1 != null){
                return ResponseEntity.ok(cliente1);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<Client> update(@RequestBody Client client) throws ApplicationException {
        Client cliente1 = clientServicePort.update(client);
        if (cliente1 != null){
            return ResponseEntity.ok(cliente1);
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<Void> delete(@ApiParam(value = "ID of Product to return",required=true) @PathVariable("idClient") int idClient) throws ApplicationException {
        Boolean cliente = clientServicePort.delete(idClient);
        if (cliente == true){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }




    //QUERY PERSONALIZADO, CONSULTA CON DATOS DE CLIENTE
    //@GetMapping("/summary/{id}")
    /*public ResponseEntity<List<Object[]>> getSummary(@PathVariable Integer id){        //revisar codigo
        List<Object[]> consultas = clienteServicio.getConsultaMascota(id);
        if (consultas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(consultas);
        }
    }*/

}
