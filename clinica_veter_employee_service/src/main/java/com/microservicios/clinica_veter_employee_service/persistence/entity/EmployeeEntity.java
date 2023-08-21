package com.microservicios.clinica_veter_employee_service.persistence.entity;

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
public class EmployeeEntity {
    @Id
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    private String nombre;

    private String direccion;

    @Column(name = "numero_celular")
    private String numeroCelular;

    private String puesto;

    @Column(name = "id_sucursal")
    private Integer idSucursal;
}
