package com.microservicios.clinica.clinica_veter_entitys.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sucursal")
@Getter
@Setter
@NoArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal", nullable = false, length = 15)
    private Integer idSucursal;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(name = "numero_celular", nullable = false,length = 50)
    private String numeroCelular;

    @Column(length = 50, unique = true)
    private String email;

    // Relación 1 a muchos con Empleado
    @OneToMany(mappedBy = "idSucursal", cascade = CascadeType.ALL)
    private List<Empleado> idEmpleado;
}
