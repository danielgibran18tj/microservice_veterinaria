package com.microservicios.clinica.clinica_veter_client_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @Column(name = "id_cliente")//, nullable = false, length = 15)
    private Integer idCliente;

    //@Column(nullable = false, length = 60)
    private String nombre;

    //@Column(nullable = false, length = 100)
    private String direccion;

    //@Column(name = "numero_celular", nullable = false,length = 50)
    private String numeroCelular;

    //@Column(length = 50)
    private String email;

    @ElementCollection
    @CollectionTable(name = "mascota", joinColumns = @JoinColumn(name = "id_cliente"))
    @Column(name = "id_mascota")
    private List<Integer> idMascotas;
}
