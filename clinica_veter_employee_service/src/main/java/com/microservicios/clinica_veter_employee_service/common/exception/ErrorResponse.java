package com.microservicios.clinica_veter_employee_service.common.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;
    private String message;
}
