package com.microservicios.clinica_veter_employee_service.web.controller;

import com.microservicios.clinica_veter_employee_service.common.exception.ApplicationException;
import com.microservicios.clinica_veter_employee_service.domain.model.Employee;
import com.microservicios.clinica_veter_employee_service.domain.service.EmployeeService;
import com.microservicios.clinica_veter_employee_service.web.controller.ifc.EmployeeAPI;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/employee")
public class EmployeeController implements EmployeeAPI {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private final HttpServletRequest request;

    private final EmployeeService employeeService;

    public EmployeeController(HttpServletRequest request, EmployeeService employeeService) {
        this.request = request;
        this.employeeService = employeeService;
    }

    public ResponseEntity<List<Employee>> getAll(){
        try {
            return new ResponseEntity<List<Employee>>(employeeService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    public ResponseEntity<Employee> getId(@ApiParam(value = "ID of Employee to return", required = true) @PathVariable("id") Integer id) throws ApplicationException {
        String accept = request.getHeader("Accept");
        if (accept!= null && accept.contains("application/json")){
            return ResponseEntity.ok(employeeService.getId(id));
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<Employee> agg(@RequestBody Employee employee) throws ApplicationException{
        Employee employee1 = employeeService.save(employee);
        try {
            if (employee1 != null){
                return ResponseEntity.ok(employee1);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<Employee> update(@RequestBody Employee employee) throws ApplicationException {
        Employee employee1 = employeeService.update(employee);
        if (employee1 != null){
            return ResponseEntity.ok(employee1);
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<String> delete(@ApiParam(value = "ID of employee", required = true) @PathVariable("idEmployee") int idEmployee) throws ApplicationException {
        Boolean employee1 = employeeService.delete(idEmployee);
        if (employee1){
            return ResponseEntity.ok("Employee successfully removed");
        }
        return ResponseEntity.badRequest().build();
    }

}
