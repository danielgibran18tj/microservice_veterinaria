package com.microservicios.clinica_veter_branch_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Branch {
    private Integer id;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;
}
