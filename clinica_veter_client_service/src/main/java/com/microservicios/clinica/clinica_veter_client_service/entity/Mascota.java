package com.microservicios.clinica.clinica_veter_client_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "mascota")
@Getter
@Setter
@NoArgsConstructor
public class Mascota {
    // Relación 1 a muchos con Consulta
    @Id
    @Column(name = "id_mascota", nullable = false)
    private Integer idMascota;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(length = 50)
    private String animal;

    @Column(length = 50)
    private String raza;

    @Column(nullable = false, length = 5)
    private Integer edad;

    @ManyToOne//(fetch = FetchType.LAZY)//consulta adicional para obtener su dato
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @JsonIgnore
    private Cliente cliente;
}
