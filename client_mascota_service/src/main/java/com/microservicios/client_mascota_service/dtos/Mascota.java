package com.microservicios.client_mascota_service.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Mascota {

    private Integer id;
    private String name;
    private String animal;
    private String race;
    private Integer age;
    private Integer idClient;
}
