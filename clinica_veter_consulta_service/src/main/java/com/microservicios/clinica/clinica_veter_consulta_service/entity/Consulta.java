package com.microservicios.clinica.clinica_veter_consulta_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
@IdClass(MascotaEmpleadoId.class)
@Getter
@Setter
@NoArgsConstructor
public class Consulta {
    // Relación muchos a uno con Mascota
    @Id
    @Column(name = "id_mascota", nullable = false,length = 15)
    private Integer idMascota;

    @ManyToOne
    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota", insertable = false, updatable = false)
    @JsonIgnore
    private Mascota mascota;

    // Relación muchos a uno con Empleado
    @Id
    @Column(name = "id_empleado", nullable = false,length = 15)
    private Integer idEmpleado;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado", insertable = false, updatable = false)
    @JsonIgnore
    private Empleado empleado;

    @Column(nullable = false, length = 60, columnDefinition = "timestamp")
    private LocalDateTime fecha;

    @Column(length = 50)
    private String antecedente;
}
