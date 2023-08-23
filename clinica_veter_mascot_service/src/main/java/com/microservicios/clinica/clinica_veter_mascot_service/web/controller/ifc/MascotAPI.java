package com.microservicios.clinica.clinica_veter_mascot_service.web.controller.ifc;

import com.microservicios.clinica.clinica_veter_mascot_service.common.exception.ApplicationException;
import com.microservicios.clinica.clinica_veter_mascot_service.domain.model.Mascot;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Validated
@RequestMapping(value = "/v1")
public interface MascotAPI {

    @Operation(summary = "Find all mascots")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Mascot.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Mascot not found") })
    @RequestMapping(value = "/mascot",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Mascot>> getAll();

    @Operation(summary = "Find mascot")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Mascot.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Mascot not found") })
    @RequestMapping(value = "/mascot/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Mascot> getId(@ApiParam(value = "ID of Mascot to return", required = true) @PathVariable("id") Integer id) throws ApplicationException;

    @Operation(summary = "Add a new Mascot to the db")
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/mascot",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Mascot> agg(@RequestBody Mascot mascot) throws ApplicationException;

    @Operation(summary = "Update an existing Mascot")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Mascot not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/mascot",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Mascot> update(@RequestBody Mascot mascot) throws ApplicationException;

    @Operation(summary = "Delete an existing Mascot")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Mascot not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/mascot/{idMascot}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@ApiParam(value = "ID of mascot", required = true) @PathVariable("idMascot") int idMascot) throws ApplicationException;

}
