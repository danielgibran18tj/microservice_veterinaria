package com.microservicios.clinica.clinica_veter_client_service.adapter.out.persistence.mapper;

import com.microservicios.clinica.clinica_veter_client_service.adapter.out.persistence.entity.ClientEntity;
import com.microservicios.clinica.clinica_veter_client_service.domain.Client;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {

    public static final ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "idCliente"),   //id -> ClientEntity
            @Mapping(target = "nombre", source = "nombre"),
            @Mapping(target = "direccion", source = "direccion"),
            @Mapping(target = "numeroCelular", source = "numeroCelular"),
            @Mapping(target = "email", source = "email")
    })
    public abstract Client toClient(ClientEntity clientEntity);

    @InheritInverseConfiguration
    public abstract ClientEntity toClientEntity(Client client);
}
