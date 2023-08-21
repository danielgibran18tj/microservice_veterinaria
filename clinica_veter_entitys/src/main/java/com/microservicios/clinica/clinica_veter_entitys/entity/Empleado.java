package com.microservicios.clinica.clinica_veter_entitys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "empleado")
@Getter
@Setter
@NoArgsConstructor
public class Empleado {
    // Relación 1 a muchos con Consulta
    @Id
    @Column(name = "id_empleado", nullable = false, length = 15)
    private Integer idEmpleado;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(name = "numero_celular", nullable = false,length = 50)
    private String numeroCelular;

    @Column(length = 30, nullable = false)
    private String puesto;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Consulta> idConsulta;

    // Relación muchos a uno con Sucursal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Sucursal idSucursal;

}
