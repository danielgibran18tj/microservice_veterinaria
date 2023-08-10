package com.ismaelviss.nttdata.adapter.out.persistence.adapter;

import com.ismaelviss.nttdata.adapter.out.persistence.mapper.ClientMapper;
import com.ismaelviss.nttdata.adapter.out.persistence.repository.ClientRepository;
import com.ismaelviss.nttdata.application.port.out.ClientPort;
import com.ismaelviss.nttdata.common.PersistenceAdapter;
import com.ismaelviss.nttdata.common.exception.ApplicationException;
import com.ismaelviss.nttdata.domain.Client;

import java.util.List;

@PersistenceAdapter
public class ClientPersistenceAdapter implements ClientPort {
    private final ClientRepository clientRepository;

    public ClientPersistenceAdapter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client get(Long id) throws ApplicationException {
        return clientRepository
                .findById(id)
                .map(ClientMapper.INSTANCE::toClient)
                .orElseThrow(() -> new ApplicationException("NOT_FOUND_CLIENT", "cliente no existe"));
    }

    @Override
    public List<Client> getAll() {
        return clientRepository
                .findAll()
                .stream()
                .map(ClientMapper.INSTANCE::toClient)
                .toList();
    }

    @Override
    public void update(Client client) throws ApplicationException {
        var clientEntityOptional = clientRepository.findById(client.getClientId());
        if (clientEntityOptional.isPresent()) {
            var clientEntity = clientEntityOptional.get();
            clientEntity.setName(client.getName());
            clientEntity.setAddress(client.getAddress());
            clientEntity.setPhoneNumber(client.getPhoneNumber());
            clientEntity.setPassword(client.getPassword());
            clientEntity.setState(client.getState());
            clientRepository.save(clientEntity);
        }
        else {
            throw new ApplicationException("NOT_FOUND_CLIENT", "cliente no existe");
        }
    }

    @Override
    public void delete(Long id) throws ApplicationException {
        var clientEntityOptional = clientRepository.findById(id);
        if (clientEntityOptional.isPresent()) {
            clientRepository.delete(clientEntityOptional.get());
        }
        else {
            throw new ApplicationException("NOT_FOUND_CLIENT", "cliente no existe");
        }
    }

    @Override
    public Client add(Client client) {
        return ClientMapper.INSTANCE.toClient(clientRepository.save(ClientMapper.INSTANCE.toClientEntity(client)));
    }
}
