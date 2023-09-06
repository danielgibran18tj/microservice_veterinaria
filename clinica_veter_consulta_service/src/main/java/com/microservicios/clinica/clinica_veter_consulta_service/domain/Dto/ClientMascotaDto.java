package com.microservicios.clinica.clinica_veter_consulta_service.domain.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientMascotaDto {
    private Integer idClienteMascota;

    private String name;
    private String animal;
    private Integer age;

    private String nombre;
    private String numeroCelular;

}
