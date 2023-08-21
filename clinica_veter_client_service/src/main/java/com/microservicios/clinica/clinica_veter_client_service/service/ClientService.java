package com.microservicios.clinica.clinica_veter_client_service.service;

import com.microservicios.clinica.clinica_veter_client_service.common.UseCase;
import com.microservicios.clinica.clinica_veter_client_service.adapter.out.persistence.repository.ClientPort;
import com.microservicios.clinica.clinica_veter_client_service.common.exception.ApplicationException;
import com.microservicios.clinica.clinica_veter_client_service.domain.Client;
import org.springframework.http.HttpStatus;

import java.util.List;

@UseCase
public class ClientService implements ClientServicePort{
    private final ClientPort clientPort;

    public ClientService(ClientPort clientPort) {
        this.clientPort = clientPort;
    }

    public List<Client> getAll() throws ApplicationException {
        return this.clientPort.getAll();
    }

    public Client getId(Integer idClient) throws ApplicationException {
        var client = this.clientPort.getId(idClient);

        if (client == null) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "NOT_FOUND_CLIENT", "cliente no existe");
        }
        return client;
    }

    public Client save(Client client) throws ApplicationException {
        if (!exists(client.getIdCliente())){
            return this.clientPort.save(client);
        }
        throw new ApplicationException(HttpStatus.FOUND, "FOUND_CLIENT", "cliente ya existe");
    }

    @Override
    public Client update(Client client) throws ApplicationException{
        if (exists(client.getIdCliente())){
            return this.clientPort.update(client);
        }
        return null;
    }

    public boolean exists(int idCliente){
        return this.clientPort.exists(idCliente);
    }

    public boolean delete(int id) throws ApplicationException{
            if (exists(id)){
                this.clientPort.delete(id);
                return true;
            }
            throw new ApplicationException(HttpStatus.NOT_FOUND, "NOT_FOUND_CLIENT", "cliente no existe");

    }
}
