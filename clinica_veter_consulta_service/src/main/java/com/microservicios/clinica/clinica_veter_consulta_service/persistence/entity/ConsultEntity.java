package com.microservicios.clinica.clinica_veter_consulta_service.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
@IdClass(IdConsultEntity.class)
@Getter
@Setter
@NoArgsConstructor
public class ConsultEntity {
    @Id
    @Column(name = "id_mascota")
    private Integer idMascota;

    @Id
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    private LocalDateTime fecha;

    private String antecedente;
}
