package com.microservicios.clinica.clinica_veter_client_service.repository.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ClienteSummary {
    Integer getIdCliente();
    String getDuenioMascota();
    String getNombreMascota();
    Integer getIdMascota();
    String getNombreDoctor();
    LocalDate getHorarioConsulta();
}
