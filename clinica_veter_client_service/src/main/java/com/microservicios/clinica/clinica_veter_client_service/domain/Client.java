package com.microservicios.clinica.clinica_veter_client_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Integer idCliente;
    private String nombre;
    private String direccion;
    private String numeroCelular;
    private String email;

}
