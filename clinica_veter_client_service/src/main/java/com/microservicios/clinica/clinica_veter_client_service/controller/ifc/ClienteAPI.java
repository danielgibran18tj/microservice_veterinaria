package com.microservicios.clinica.clinica_veter_client_service.controller.ifc;

import com.microservicios.clinica.clinica_veter_client_service.entity.Cliente;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Validated

@RequestMapping(value = "/v1")
public interface ClienteAPI {

    @ApiOperation(value = "Find client", nickname = "getClients", notes = "Returns all clients", response = Cliente.class, tags={ "client", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Cliente.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Client not found") })
    @RequestMapping(value = "/client",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Cliente>> getAll();


    @ApiOperation(value = "Find client by ID", nickname = "getClientById", notes = "Returns a single client", response = Cliente.class, tags={ "client", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Cliente.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Client not found") })
    @RequestMapping(value = "/client/{idClient}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Cliente> getId(@ApiParam(value = "ID of Client to return",required=true) @PathVariable("idClient") Integer idCliente);


    @ApiOperation(value = "Add a new Client to the db", nickname = "addClient", notes = "", tags={ "client", })
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/client",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Cliente> agg(@RequestBody Cliente cliente);


    @ApiOperation(value = "Update an existing Client", nickname = "updateClient", notes = "", tags={ "client", })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Client not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/client",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Cliente> update(@RequestBody Cliente cliente);


    @ApiOperation(value = "Delete an existing Client", nickname = "deleteClient", notes = "", tags={ "client", })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Client not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/client/{idClient}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@ApiParam(value = "ID of Client",required=true) @PathVariable("idClient") int idClient);

    }
