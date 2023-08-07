package com.microservicios.clinica.clinica_veter_client_service.service;

import com.microservicios.clinica.clinica_veter_client_service.entity.Cliente;
import com.microservicios.clinica.clinica_veter_client_service.repository.ClienteRespositorio;
import com.microservicios.clinica.clinica_veter_client_service.repository.projection.ClienteSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {
    private final ClienteRespositorio clienteRespositorio;

    @Autowired
    public ClienteServicio(ClienteRespositorio clienteRespositorio) {
        this.clienteRespositorio = clienteRespositorio;
    }

    public List<Cliente> getAll(){
        return (List<Cliente>) this.clienteRespositorio.findAll();
    }

    public Cliente getId(int idCliente){
        return this.clienteRespositorio.findById(idCliente).orElse(null);
    }

    public List<Object[]> getConsultaMascota(Integer clienteId){
        return null;//this.clienteRespositorio.obtenerDatosConsultaPorClienteId(clienteId);
    }

    public Cliente save(Cliente cliente){
        return this.clienteRespositorio.save(cliente);
    }

    public boolean exists(int idCliente){
        return this.clienteRespositorio.existsById(idCliente);
    }

    public void delete(int id){
        this.clienteRespositorio.deleteById(id);
    }
}
