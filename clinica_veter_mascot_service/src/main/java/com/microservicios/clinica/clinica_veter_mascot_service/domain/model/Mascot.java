package com.microservicios.clinica.clinica_veter_mascot_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mascot {
    private Integer id;
    private String name;
    private String animal;
    private String race;
    private Integer age;
    private Integer idClient;
}
