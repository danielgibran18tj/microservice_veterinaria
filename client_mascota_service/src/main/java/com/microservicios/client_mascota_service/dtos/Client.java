package com.microservicios.client_mascota_service.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Client {
    private Integer idCliente;
    private String nombre;
    private String direccion;
    private String numeroCelular;
    private String email;

}
