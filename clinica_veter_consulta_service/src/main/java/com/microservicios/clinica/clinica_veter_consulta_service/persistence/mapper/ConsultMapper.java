package com.microservicios.clinica.clinica_veter_consulta_service.persistence.mapper;

import com.microservicios.clinica.clinica_veter_consulta_service.persistence.entity.ConsultEntity;
import com.microservicios.clinica.clinica_veter_consulta_service.domain.model.Consult;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ConsultMapper {
    public static final ConsultMapper INSTANCE = Mappers.getMapper(ConsultMapper.class);

    @Mappings({
            @Mapping(target = "idMascota", source = "idMascota"),
            @Mapping(target = "idEmpleado", source = "idEmpleado"),
            @Mapping(target = "fecha", source = "fecha"),
            @Mapping(target = "antecedente", source = "antecedente")
    })
    public abstract Consult toConsult(ConsultEntity consultEntity);

    @InheritInverseConfiguration
    public abstract ConsultEntity toConsultEntity(Consult consult);
}
