package com.microservicios.clinica_veter_branch_service.web.controller.ifc;

import com.microservicios.clinica_veter_branch_service.common.exception.ApplicationException;
import com.microservicios.clinica_veter_branch_service.domain.model.Branch;
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
public interface BranchAPI {


    @Operation(summary = "Find all branch")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Branch.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Branch not found") })
    @RequestMapping(value = "/branch",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Branch>> getAll();



    @Operation(summary = "Find branch")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Branch.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "branch not found") })
    @RequestMapping(value = "/branch/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Branch> getId(@ApiParam(value = "ID of branch to return", required = true) @PathVariable("id") Integer id) throws ApplicationException;

    @Operation(summary = "Add a new branch to the db")
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/branch",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Branch> agg(@RequestBody Branch branch) throws ApplicationException;

    @Operation(summary = "Update an existing branch")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Branch not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/branch",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Branch> update(@RequestBody Branch branch) throws ApplicationException;

    @Operation(summary = "Delete an existing branch")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "branch not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/branch/{idBranch}",
            method = RequestMethod.DELETE)
    ResponseEntity<String> delete(@ApiParam(value = "ID of branch", required = true) @PathVariable("idBranch") int idBranch) throws ApplicationException;

}
