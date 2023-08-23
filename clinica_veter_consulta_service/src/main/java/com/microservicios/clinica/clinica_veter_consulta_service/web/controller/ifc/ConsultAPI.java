package com.microservicios.clinica.clinica_veter_consulta_service.web.controller.ifc;

import com.microservicios.clinica.clinica_veter_consulta_service.common.exception.ApplicationException;
import com.microservicios.clinica.clinica_veter_consulta_service.domain.model.Consult;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping(value = "/v1")
public interface ConsultAPI {


    @Operation(summary = "Find all consult")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Consult.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Consult not found") })
    @RequestMapping(value = "/consult",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Consult>> getAll();


    @Operation(summary = "Add a new Consult to the db")
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/consult",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Consult> agg(@RequestBody Consult consult) throws ApplicationException;


    @Operation(summary = "Update an existing Consult")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Consult not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/consult",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Consult> update(@RequestBody Consult consult) throws ApplicationException;


    @Operation(summary = "Find consult")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Consult.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Consult not found") })
    @RequestMapping(value = "/consult/{idMascota}/{idEmpleado}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Consult> getId(@ApiParam(value = "ID of Consult to return", required = true) @PathVariable Integer idMascota, @PathVariable Integer idEmpleado) throws ApplicationException;


    @Operation(summary = "Delete an existing Consult")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Consult not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/consult/{idMascota}/{idEmpleado}",
            method = RequestMethod.DELETE)
        ResponseEntity<String> deleteConsultaById(@PathVariable Integer idMascota, @PathVariable Integer idEmpleado);


}
