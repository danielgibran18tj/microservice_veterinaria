package com.microservicios.clinica_veter_employee_service.web.controller.ifc;

import com.microservicios.clinica_veter_employee_service.common.exception.ApplicationException;
import com.microservicios.clinica_veter_employee_service.domain.model.Employee;
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
public interface EmployeeAPI {


    @Operation(summary = "Find all employees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Employee.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Employee not found") })
    @RequestMapping(value = "/employee",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Employee>> getAll();



    @Operation(summary = "Find employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Employee.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Employee not found") })
    @RequestMapping(value = "/employee/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Employee> getId(@ApiParam(value = "ID of Employee to return", required = true) @PathVariable("id") Integer id) throws ApplicationException;

    @Operation(summary = "Add a new Employee to the db")
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/employee",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Employee> agg(@RequestBody Employee employee) throws ApplicationException;

    @Operation(summary = "Update an existing Employee")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Employee not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/employee",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Employee> update(@RequestBody Employee employee) throws ApplicationException;

    @Operation(summary = "Delete an existing Employee")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Employee not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/employee/{idEmployee}",
            method = RequestMethod.DELETE)
    ResponseEntity<String> delete(@ApiParam(value = "ID of employee", required = true) @PathVariable("idEmployee") int idEmployee) throws ApplicationException;


}
