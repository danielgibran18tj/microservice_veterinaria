package com.microservicios.clinica_veter_branch_service.persintence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sucursal")
@Getter
@Setter
@NoArgsConstructor
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Integer idSucursal;

    private String nombre;

    private String direccion;

    @Column(name = "numero_celular")
    private String numeroCelular;

    private String email;

}
