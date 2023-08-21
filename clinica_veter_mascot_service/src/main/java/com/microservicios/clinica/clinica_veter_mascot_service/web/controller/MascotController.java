package com.microservicios.clinica.clinica_veter_mascot_service.web.controller;

import com.microservicios.clinica.clinica_veter_mascot_service.common.exception.ApplicationException;
import com.microservicios.clinica.clinica_veter_mascot_service.domain.model.Mascot;
import com.microservicios.clinica.clinica_veter_mascot_service.domain.service.MascotService;
import com.microservicios.clinica.clinica_veter_mascot_service.web.controller.ifc.MascotAPI;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascot")
public class MascotController implements MascotAPI {
    private static final Logger log = LoggerFactory.getLogger(MascotController.class);

    private final HttpServletRequest request;

    private final MascotService mascotService;

    public MascotController(HttpServletRequest request, MascotService mascotService) {
        this.request = request;
        this.mascotService = mascotService;
    }

    public ResponseEntity<List<Mascot>> getAll(){
        try {
            return new ResponseEntity<List<Mascot>>(mascotService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<Mascot>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Mascot> getId(@ApiParam(value = "ID of Mascot to return", required = true) @PathVariable("id") Integer id) throws ApplicationException {
        String accept = request.getHeader("Accept");
        if (accept!= null && accept.contains("application/json")){
            return ResponseEntity.ok(mascotService.getId(id));
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<Mascot> agg(@RequestBody Mascot mascot) throws ApplicationException {
        Mascot mascot1 = mascotService.save(mascot);
        try {
            if (mascot1 != null){
                return ResponseEntity.ok(mascot1);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<Mascot> update(@RequestBody Mascot mascot) throws ApplicationException{
        Mascot mascot1 = mascotService.update(mascot);
        if (mascot1 != null){
            return ResponseEntity.ok(mascot1);
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<Void> delete(@ApiParam(value = "ID of mascot", required = true) @PathVariable("idMascot") int idMascot) throws ApplicationException{
        Boolean mascot1 = mascotService.delete(idMascot);
        if (mascot1 == true){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
