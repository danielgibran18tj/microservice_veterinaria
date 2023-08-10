package com.microservicios.clinica.clinica_veter_client_service.service;

import com.microservicios.clinica.clinica_veter_client_service.adapter.entity.ClientEntity;
import com.microservicios.clinica.clinica_veter_client_service.repository.ClienteCrupRespositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {
    private final ClienteCrupRespositorio clienteCrupRespositorio;

    @Autowired
    public ClienteServicio(ClienteCrupRespositorio clienteCrupRespositorio) {
        this.clienteCrupRespositorio = clienteCrupRespositorio;
    }

    public List<ClientEntity> getAll(){
        return (List<ClientEntity>) this.clienteCrupRespositorio.findAll();
    }

    public ClientEntity getId(Integer idCliente){
        return this.clienteCrupRespositorio.findById(idCliente).orElse(null);
    }

    public ClientEntity save(ClientEntity clientEntity){
        if (!exists(clientEntity.getId())){
            return this.clienteCrupRespositorio.save(clientEntity);
        }
        return null;
    }

    public ClientEntity update(ClientEntity clientEntity){
        if (exists(clientEntity.getId())){
            return this.clienteCrupRespositorio.save(clientEntity);
        }
        return null;
    }

    public boolean exists(int idCliente){
        return this.clienteCrupRespositorio.existsById(idCliente);
    }

    public boolean delete(int id){
        if (exists(id)){
            this.clienteCrupRespositorio.deleteById(id);
            return true;
        }
        return false;
    }
}
