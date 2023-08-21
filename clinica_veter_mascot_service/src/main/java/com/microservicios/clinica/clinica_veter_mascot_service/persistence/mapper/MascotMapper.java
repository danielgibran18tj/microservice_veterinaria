package com.microservicios.clinica.clinica_veter_mascot_service.persistence.mapper;

import com.microservicios.clinica.clinica_veter_mascot_service.domain.model.Mascot;
import com.microservicios.clinica.clinica_veter_mascot_service.persistence.entity.MascotEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MascotMapper {

    public static final MascotMapper INSTANCE = Mappers.getMapper(MascotMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "idMascota"),
            @Mapping(target = "name", source = "nombre"),
            @Mapping(target = "animal", source = "animal"),
            @Mapping(target = "race", source = "raza"),
            @Mapping(target = "age", source = "edad"),
            @Mapping(target = "idClient", source = "idCliente"),
    })
    public abstract Mascot toMascot (MascotEntity mascotEntity);

    @InheritInverseConfiguration
    public abstract MascotEntity toMascotEntity (Mascot mascot);

}
