package com.microservicios.clinica.clinica_veter_entitys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Mascota mascota;

    // Relación muchos a uno con Empleado
    @Id
    @Column(name = "id_empleado", nullable = false,length = 15)
    private Integer idEmpleado;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Empleado empleado;

    @Column(nullable = false, length = 60, columnDefinition = "timestamp")
    private LocalDateTime fecha;

    @Column(length = 50)
    private String antecedente;
}
