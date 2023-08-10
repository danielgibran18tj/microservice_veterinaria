package com.microservicios.clinica.clinica_veter_client_service.service;

import com.microservicios.clinica.clinica_veter_client_service.adapter.out.persistence.entity.ClientEntity;
import com.microservicios.clinica.clinica_veter_client_service.common.exception.ApplicationException;
import com.microservicios.clinica.clinica_veter_client_service.domain.Client;

import java.util.List;

public interface ClientServicePort {
    List<Client> getAll() throws ApplicationException ;

    Client getId(Integer idClient) throws ApplicationException;

    Client save(Client client);

    Client update(Client client) throws ApplicationException;

    boolean exists(int idClient);

    boolean delete(int id) throws ApplicationException ;
}
