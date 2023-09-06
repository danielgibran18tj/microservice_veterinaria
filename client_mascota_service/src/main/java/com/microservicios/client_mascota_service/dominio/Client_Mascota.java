package com.microservicios.client_mascota_service.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client_Mascota {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idClienteMascota;

    private Integer id;
    private String name;
    private String animal;
    private String race;
    private Integer age;

    private Integer idCliente;
    private String nombre;
    private String direccion;
    private String numeroCelular;
    private String email;

}
