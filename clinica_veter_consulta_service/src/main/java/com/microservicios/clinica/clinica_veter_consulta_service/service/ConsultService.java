package com.microservicios.clinica.clinica_veter_consulta_service.service;

import com.microservicios.clinica.clinica_veter_consulta_service.adapter.out.persistence.entity.ConsultEntity;
import com.microservicios.clinica.clinica_veter_consulta_service.adapter.out.persistence.mapper.ConsultMapper;
import com.microservicios.clinica.clinica_veter_consulta_service.adapter.out.persistence.repository.ConsultRepository;

import com.microservicios.clinica.clinica_veter_consulta_service.domain.Consult;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultService {
    private final ConsultRepository consultRepository;

    @Autowired
    public ConsultService(ConsultRepository consultRepository) {
        this.consultRepository = consultRepository;
    }

    public List<Consult> getAll(){
        //List<Consulta> consultas =  this.consultaRepositorio.findAll();
        return consultRepository
                .findAll()
                .stream()
                .map(ConsultMapper.INSTANCE::toConsult)
                .toList();
    }

    /*public Consult save(Consult consult){
        return ConsultMapper.INSTANCE.toConsult(
                consultRepository.save(
                        ConsultMapper.INSTANCE.toConsultEntity(consult)
                )
        );
    }*/

    /*public boolean exists(MascotaEmpleadoId idConsulta){
        return this.consultRepository.existsById(idConsulta);
    }*/

    /*@Transactional
    public void deleteConsultaById(Integer idEmpleado, Integer idMascota) {
        //consultRepository.deleteByCompositeKey(idEmpleado, idMascota);
    }*/
}
