package com.microservicios.clinica.clinica_veter_consulta_service.domain.service;

import com.microservicios.clinica.clinica_veter_consulta_service.common.exception.ApplicationException;
import com.microservicios.clinica.clinica_veter_consulta_service.persistence.entity.ConsultEntity;
import com.microservicios.clinica.clinica_veter_consulta_service.persistence.entity.IdConsultEntity;
import com.microservicios.clinica.clinica_veter_consulta_service.persistence.mapper.ConsultMapper;
import com.microservicios.clinica.clinica_veter_consulta_service.persistence.ConsultRepository;

import com.microservicios.clinica.clinica_veter_consulta_service.domain.model.Consult;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultService {
    private final ConsultRepository consultRepository;

    @Autowired
    public ConsultService(ConsultRepository consultRepository) {
        this.consultRepository = consultRepository;
    }

    public List<Consult> getAll(){
        return consultRepository
                .findAll()
                .stream()
                .map(ConsultMapper.INSTANCE::toConsult)
                .toList();
    }


    public Consult getId(Integer idMascota, Integer idEmpleado) throws ApplicationException {
        if (exists(idMascota, idEmpleado)){
            var consultEntity1 = consultRepository.findByIdMascotaAndIdEmpleado(idMascota, idEmpleado);
            return ConsultMapper.INSTANCE.toConsult(consultEntity1);
        }else {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "NOT_FOUND_CONSULT", "id does not exist");
        }
    }


    public Consult save(Consult consult) throws ApplicationException {
        LocalDateTime newConsultStartTime = consult.getFecha().minusMinutes(59);
        LocalDateTime newConsultEndTime = consult.getFecha().plusMinutes(59);
        if (!exists(consult.getIdMascota(), consult.getIdEmpleado())){
            if (hasNoConflictingConsult(newConsultStartTime, newConsultEndTime, consult.getIdEmpleado())){
                try {
                    return ConsultMapper.INSTANCE.toConsult(
                            consultRepository.save(
                                    ConsultMapper.INSTANCE.toConsultEntity(consult)));
                }catch (Exception e){
                    throw new ApplicationException(HttpStatus.BAD_REQUEST, "BAD_REQUEST" , "id does not exist");
                }
            }else {
                throw new ApplicationException(HttpStatus.BAD_REQUEST, "BAD_REQUEST" , "conflict with schedule");
            }
        }else {
            throw new ApplicationException( HttpStatus.NOT_FOUND, "FOUND_CONSULT", "consult does exist");
        }
    }


    public Consult update(Consult consult) throws ApplicationException {
        LocalDateTime newConsultStartTime = consult.getFecha().minusMinutes(59);
        LocalDateTime newConsultEndTime = consult.getFecha().plusMinutes(59);
        if (hasNoConflictingConsult(newConsultStartTime, newConsultEndTime, consult.getIdEmpleado())){
            if (exists(consult.getIdMascota(), consult.getIdEmpleado())){
                var consultEntity1 = consultRepository.findByIdMascotaAndIdEmpleado(consult.getIdMascota(), consult.getIdEmpleado());
                consultEntity1.setAntecedente(consult.getAntecedente());
                consultEntity1.setFecha(consult.getFecha());
                consultRepository.save(consultEntity1);
                return consult;
            }else {
                throw new ApplicationException(HttpStatus.NOT_FOUND, "NOT_FOUND_CONSULT", "consult does not exist");
            }
        }else {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "BAD_REQUEST" , "conflict with schedule");
        }
    }


    @Transactional
    public Boolean deleteConsultByIds(Integer idMascota, Integer idEmpleado) {
        if (exists(idMascota,idEmpleado)){
            consultRepository.deleteByConsultId(idMascota, idEmpleado);
            return true;
        }else {
            return false;
        }
    }


    public boolean exists(Integer idMascota , Integer idEmpleado){
        return this.consultRepository.existsByIdMascotaAndIdEmpleado(idMascota, idEmpleado );
    }

    private boolean hasNoConflictingConsult(LocalDateTime startTime, LocalDateTime endTime, Integer idEmpleado) {
        List<ConsultEntity> conflictingConsults = consultRepository.findConflictingConsultsForEmployee(startTime, endTime, idEmpleado);
        return conflictingConsults.isEmpty();
    }

}
