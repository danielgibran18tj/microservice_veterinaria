package com.microservicios.clinica.clinica_veter_consulta_service.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CitaFeign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer idClienteMascota;

    private String nameMascota;
    private String animal;
    private Integer age;

    private String nombreCliente;
    private String numeroCelular;

}
