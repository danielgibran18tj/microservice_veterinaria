package com.microservicios.clinica.clinica_veter_consulta_service.domain.infraestructura;

import com.microservicios.clinica.clinica_veter_consulta_service.domain.Dto.ClientMascotaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientmascota-api")
public interface ConsultFeignController {

    @GetMapping("/clientMascota/{id}")
    ClientMascotaDto obtenerPorId(@PathVariable("id") Integer id);
}
