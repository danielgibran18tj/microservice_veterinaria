package com.microservicios.clinica_veter_employee_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private String job;
    private Integer idBranch;
}
