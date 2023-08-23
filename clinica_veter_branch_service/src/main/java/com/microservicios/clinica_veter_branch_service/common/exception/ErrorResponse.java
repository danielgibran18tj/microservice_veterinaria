package com.microservicios.clinica_veter_branch_service.common.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;
    private String message;
}
