package com.microservicios.clinica.clinica_veter_consulta_service.service;

import com.microservicios.clinica.clinica_veter_consulta_service.entity.Consulta;
import com.microservicios.clinica.clinica_veter_consulta_service.entity.MascotaEmpleadoId;
import com.microservicios.clinica.clinica_veter_consulta_service.repository.ConsultaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaServicio {
    private final ConsultaRepositorio consultaRepositorio;

    @Autowired
    public ConsultaServicio(ConsultaRepositorio consultaRepositorio) {
        this.consultaRepositorio = consultaRepositorio;
    }

    public List<Consulta> getAll(){
        List<Consulta> consultas =  this.consultaRepositorio.findAll();
        return consultas;
    }

    public Consulta save(Consulta consulta){
        return this.consultaRepositorio.save(consulta);
    }

    public boolean exists(MascotaEmpleadoId idConsulta){
        return this.consultaRepositorio.existsById(idConsulta);
    }

    @Transactional
    public void deleteConsultaById(Integer idEmpleado, Integer idMascota) {
        consultaRepositorio.deleteByCompositeKey(idEmpleado, idMascota);
    }
}
