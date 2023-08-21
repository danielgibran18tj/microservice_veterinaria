package com.microservicios.clinica.clinica_veter_client_service.adapter.out.persistence.adapter;

import com.microservicios.clinica.clinica_veter_client_service.adapter.out.persistence.mapper.ClientMapper;
import com.microservicios.clinica.clinica_veter_client_service.adapter.out.persistence.repository.ClientCrudRepository;
import com.microservicios.clinica.clinica_veter_client_service.adapter.out.persistence.repository.ClientPort;
import com.microservicios.clinica.clinica_veter_client_service.common.PersistenceAdapter;
import com.microservicios.clinica.clinica_veter_client_service.common.exception.ApplicationException;
import com.microservicios.clinica.clinica_veter_client_service.domain.Client;
import org.springframework.http.HttpStatus;

import java.util.List;

@PersistenceAdapter
public class ClientPersistenceAdapter implements ClientPort {
    private final ClientCrudRepository clientCrudRepository;

    public ClientPersistenceAdapter(ClientCrudRepository clientCrudRepository) {
        this.clientCrudRepository = clientCrudRepository;
    }

    @Override
    public List<Client> getAll(){
        return clientCrudRepository
                .findAll()
                .stream()
                .map(ClientMapper.INSTANCE::toClient)
                .toList();
    }

    @Override
    public Client getId(Integer idClient) throws ApplicationException {
        return clientCrudRepository
                .findById(idClient)
                .map(ClientMapper.INSTANCE::toClient)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, "NOT_FOUND_CLIENT", "cliente no existe"));
    }

    @Override
    public Client save(Client client) {
        return ClientMapper.INSTANCE.toClient(clientCrudRepository.save(ClientMapper.INSTANCE.toClientEntity(client)));
    }

    @Override
    public Client update(Client client) throws ApplicationException {
        var clientEntityOptional = clientCrudRepository.findById(client.getIdCliente());
        if (clientEntityOptional.isPresent()) {
            var clientEntity = clientEntityOptional.get();
            clientEntity.setNombre(client.getNombre());
            clientEntity.setDireccion(client.getDireccion());
            clientEntity.setNumeroCelular(client.getNumeroCelular());
            clientEntity.setEmail(client.getEmail());
            clientCrudRepository.save(clientEntity);
            return client;
        }
        else {
            throw new ApplicationException( "NOT_FOUND_CLIENT", "cliente no existe");
        }
    }

    @Override
    public boolean exists(int idClient) {
        return this.clientCrudRepository.existsById(idClient);
    }

    @Override
    public boolean delete(int id) {
        clientCrudRepository.deleteById(id);
        return true;
    }

}
