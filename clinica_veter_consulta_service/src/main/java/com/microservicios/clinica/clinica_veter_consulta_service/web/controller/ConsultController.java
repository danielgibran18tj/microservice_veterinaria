package com.microservicios.clinica.clinica_veter_consulta_service.web.controller;

import com.microservicios.clinica.clinica_veter_consulta_service.common.exception.ApplicationException;
import com.microservicios.clinica.clinica_veter_consulta_service.domain.model.Consult;
import com.microservicios.clinica.clinica_veter_consulta_service.domain.service.ConsultService;
import com.microservicios.clinica.clinica_veter_consulta_service.web.controller.ifc.ConsultAPI;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consult")
public class ConsultController  implements ConsultAPI {

    private static final Logger log = LoggerFactory.getLogger(ConsultController.class);
    private final HttpServletRequest request;
    private final ConsultService consultService;

    @Autowired
    public ConsultController(HttpServletRequest request, ConsultService consultService) {
        this.request = request;
        this.consultService = consultService;
    }


    public ResponseEntity<List<Consult>> getAll(){
        try {
            return new ResponseEntity<List<Consult>>(consultService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<Consult>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<Consult> agg(Consult consult) throws ApplicationException {
        Consult consult1 = consultService.save(consult);
        if (consult1 != null){
            return ResponseEntity.ok(consult1);
        }
        return ResponseEntity.badRequest().build();
    }


    @Override
    public ResponseEntity<Consult> update(Consult consult) throws ApplicationException {
        Consult consult1 = consultService.update(consult);
        if (consult1 != null){
            return ResponseEntity.ok(consult1);
        }
        return ResponseEntity.notFound().build();
    }



    @Override
    public ResponseEntity<Consult> getId(Integer idMascota, Integer idEmpleado) throws ApplicationException {
        String accept = request.getHeader("Accept");
        if (accept!= null && accept.contains("application/json")){
            Consult consult1 = consultService.getId(idMascota, idEmpleado);
                return ResponseEntity.ok(consult1);
            }
            return ResponseEntity.notFound().build();
    }



    public ResponseEntity<String> deleteConsultaById(@PathVariable Integer idMascota, @PathVariable Integer idEmpleado) {
        Boolean consult1 = consultService.deleteConsultByIds(idMascota, idEmpleado);
        if (consult1){
            return ResponseEntity.ok("Consult successfully removed");
        }
        return ResponseEntity.notFound().build();
    }
}
