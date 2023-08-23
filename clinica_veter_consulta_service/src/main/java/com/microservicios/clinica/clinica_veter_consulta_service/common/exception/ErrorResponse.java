package com.microservicios.clinica.clinica_veter_consulta_service.common.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;
    private String message;
}
