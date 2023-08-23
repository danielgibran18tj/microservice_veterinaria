package com.microservicios.clinica_veter_branch_service.persintence.mapper;

import com.microservicios.clinica_veter_branch_service.domain.model.Branch;
import com.microservicios.clinica_veter_branch_service.persintence.entity.BranchEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class BranchMapper {
    public static final BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "idSucursal"),
            @Mapping(target = "name", source = "nombre"),
            @Mapping(target = "address", source = "direccion"),
            @Mapping(target = "phoneNumber", source = "numeroCelular"),
            @Mapping(target = "email", source = "email")
    })
    public abstract Branch toBranch (BranchEntity branchEntity);

    @InheritInverseConfiguration
    public abstract BranchEntity toBranchEntity (Branch branch);

}
