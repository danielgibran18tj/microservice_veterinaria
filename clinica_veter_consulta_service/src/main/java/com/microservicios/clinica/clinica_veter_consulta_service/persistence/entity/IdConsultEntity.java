package com.microservicios.clinica.clinica_veter_consulta_service.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdConsultEntity implements Serializable {
    private Integer idMascota;
    private Integer idEmpleado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdConsultEntity that = (IdConsultEntity) o;
        return Objects.equals(idMascota, that.idMascota) && Objects.equals(idEmpleado, that.idEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMascota, idEmpleado);
    }
}
