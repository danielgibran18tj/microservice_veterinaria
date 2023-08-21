package com.microservicios.clinica_veter_employee_service.domain.service;

import com.microservicios.clinica_veter_employee_service.common.exception.ApplicationException;
import com.microservicios.clinica_veter_employee_service.domain.model.Employee;
import com.microservicios.clinica_veter_employee_service.persistence.EmployeeCrudRepository;
import com.microservicios.clinica_veter_employee_service.persistence.mapper.EmployeeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeCrudRepository employeeCrudRepository;

    public EmployeeService(EmployeeCrudRepository employeeCrudRepository) {
        this.employeeCrudRepository = employeeCrudRepository;
    }

    public List<Employee> getAll(){
        return employeeCrudRepository
                .findAll()
                .stream()
                .map(EmployeeMapper.INSTANCE::toEmployee)
                .toList();
    }

    public Employee getId(Integer idEmployee) throws ApplicationException {
        return employeeCrudRepository
                .findById(idEmployee)
                .map(EmployeeMapper.INSTANCE::toEmployee)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, "NOT_FOUND_EMPLOYEE", "employee does not exist"));
    }

    public Employee save(Employee employee) throws ApplicationException {
        if (!exists(employee.getId())){
            return EmployeeMapper.INSTANCE.toEmployee(employeeCrudRepository.save(EmployeeMapper.INSTANCE.toEmployeeEntity(employee)));
        }
        throw new ApplicationException(HttpStatus.FOUND, "FOUND_CLIENT", "id mascot exist");

    }

    public Employee update(Employee employee) throws ApplicationException {
        if (exists(employee.getId())){
            var employeeEntity1 = employeeCrudRepository.findById(employee.getId()).get();
            employeeEntity1.setNombre(employee.getName());
            employeeEntity1.setDireccion(employee.getAddress());
            employeeEntity1.setNumeroCelular(employee.getPhoneNumber());
            employeeEntity1.setPuesto(employee.getJob());
            employeeEntity1.setIdSucursal(employee.getIdBranch());
            employeeCrudRepository.save(employeeEntity1);
            return employee;
        }else {
            throw new ApplicationException( "NOT_FOUND_EMPLOYEE", "employee does not exist");
        }
    }


    public Boolean delete(int idEmployee) throws ApplicationException {
        if (exists(idEmployee)){
            employeeCrudRepository.deleteById(idEmployee);
            return true;
        }
        throw new ApplicationException(HttpStatus.NOT_FOUND, "NOT_FOUND_EMPLOYEE", "employee does not exist");
    }


    public boolean exists(int idClient){
        return this.employeeCrudRepository.existsById(idClient);
    }
}
