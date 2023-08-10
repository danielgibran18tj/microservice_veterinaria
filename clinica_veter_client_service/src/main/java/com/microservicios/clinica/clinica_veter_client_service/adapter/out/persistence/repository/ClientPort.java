package com.microservicios.clinica.clinica_veter_client_service.repository;

import com.microservicios.clinica.clinica_veter_client_service.adapter.entity.ClientEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClientRepository {

    List<ClientEntity> getAll();

    ClientEntity getId(Integer idCliente) throws ApplicationException;

    ClientEntity save(ClientEntity clientEntity);

    ClientEntity update(ClientEntity clientEntity) throws ApplicationException;

    public boolean exists(int idCliente) throws ApplicationException;

    public boolean delete(int id) throws ApplicationException;
}
