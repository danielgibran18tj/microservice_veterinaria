package com.microservicios.clinica.clinica_veter_mascot_service.domain.service;

import com.microservicios.clinica.clinica_veter_mascot_service.common.exception.ApplicationException;
import com.microservicios.clinica.clinica_veter_mascot_service.domain.model.Mascot;
import com.microservicios.clinica.clinica_veter_mascot_service.persistence.MascotCrudRepository;
import com.microservicios.clinica.clinica_veter_mascot_service.persistence.mapper.MascotMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotService {
    private final MascotCrudRepository mascotCrudRepository;

    public MascotService(MascotCrudRepository mascotCrudRepository) {
        this.mascotCrudRepository = mascotCrudRepository;
    }

    public List<Mascot> getAll(){
        return this.mascotCrudRepository
                .findAll()
                .stream()
                .map(MascotMapper.INSTANCE::toMascot)
                .toList();
    }


    public Mascot getId(Integer idMascot) throws ApplicationException {
        return mascotCrudRepository
                .findById(idMascot)
                .map(MascotMapper.INSTANCE::toMascot)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, "NOT_FOUND_MASCOT", "mascot does not exist"));
    }

    public Mascot save(Mascot mascot) throws ApplicationException {
        if (mascot.getId() == null){
            return MascotMapper.INSTANCE.toMascot(mascotCrudRepository.save(MascotMapper.INSTANCE.toMascotEntity(mascot)));
        }
        throw new ApplicationException(HttpStatus.FOUND, "FOUND_CLIENT", "id mascot exist");

    }

    public Mascot update(Mascot mascot) throws ApplicationException {
        if (exists(mascot.getId())){
            var mascotEntity = mascotCrudRepository.findById(mascot.getId()).get();
            mascotEntity.setNombre(mascot.getName());
            mascotEntity.setAnimal(mascot.getAnimal());
            mascotEntity.setRaza(mascot.getRace());
            mascotEntity.setEdad(mascot.getAge());
            mascotEntity.setIdCliente(mascot.getIdClient());
            mascotCrudRepository.save(mascotEntity);
            return mascot;
        }else {
            throw new ApplicationException( "NOT_FOUND_CLIENT", "mascot does not exist");
        }
    }


    public Boolean delete(int idMascot) throws ApplicationException {
        if (exists(idMascot)){
            mascotCrudRepository.deleteById(idMascot);
            return true;
        }
        throw new ApplicationException(HttpStatus.NOT_FOUND, "NOT_FOUND_CLIENT", "cliente no existe");
    }


    public boolean exists(int idClient){
        return this.mascotCrudRepository.existsById(idClient);
    }
}
