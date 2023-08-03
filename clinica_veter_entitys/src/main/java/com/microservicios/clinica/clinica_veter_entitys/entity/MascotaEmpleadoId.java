package com.microservicios.clinica.clinica_veter_entitys.entity;

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
public class MascotaEmpleadoId implements Serializable {
    private Integer idMascota;
    private Integer idEmpleado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MascotaEmpleadoId that = (MascotaEmpleadoId) o;
        return Objects.equals(idMascota, that.idMascota) && Objects.equals(idEmpleado, that.idEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMascota, idEmpleado);
    }
}
