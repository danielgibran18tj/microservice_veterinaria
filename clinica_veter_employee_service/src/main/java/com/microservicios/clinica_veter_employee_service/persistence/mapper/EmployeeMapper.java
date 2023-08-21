package com.microservicios.clinica_veter_employee_service.persistence.mapper;

import com.microservicios.clinica_veter_employee_service.domain.model.Employee;
import com.microservicios.clinica_veter_employee_service.persistence.entity.EmployeeEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {
    public static final EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "idEmpleado"),
            @Mapping(target = "name", source = "nombre"),
            @Mapping(target = "address", source = "direccion"),
            @Mapping(target = "phoneNumber", source = "numeroCelular"),
            @Mapping(target = "job", source = "puesto"),
            @Mapping(target = "idBranch", source = "idSucursal"),
    })
    public abstract Employee toEmployee (EmployeeEntity employeeEntity);

    @InheritInverseConfiguration
    public abstract EmployeeEntity toEmployeeEntity (Employee employee);


}
