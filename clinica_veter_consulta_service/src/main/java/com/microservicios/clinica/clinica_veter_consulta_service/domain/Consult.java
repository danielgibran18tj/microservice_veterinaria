package com.microservicios.clinica.clinica_veter_consulta_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consult {
    private Integer idMascota;
    private Integer idEmpleado;
    private LocalDateTime fecha;
    private String antecedente;
}
