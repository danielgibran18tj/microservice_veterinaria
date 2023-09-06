package com.microservicios.client_mascota_service.controller;

import com.microservicios.client_mascota_service.dominio.Client_Mascota;
import com.microservicios.client_mascota_service.dominio.repository.ClienteMascotaRepository;
import com.microservicios.client_mascota_service.dtos.Client;
import com.microservicios.client_mascota_service.dtos.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class clientMascotaController {

    @Autowired
    private ClienteMascotaRepository clienteMascotaRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/clientMascota")
    public List<Client_Mascota> obtenerTodas() {
        return clienteMascotaRepository.findAll();
    }


    @PostMapping("/clientMascota")
    public Client_Mascota agregarCliente(@RequestBody Client_Mascota client_mascota){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Client> clientEntity = restTemplate.getForEntity("http://localhost:8086/api/cliente/client/" + client_mascota.getIdCliente(), Client.class);
        ResponseEntity<Mascota> mascotaEntity = restTemplate.getForEntity("http://localhost:8088/api/mascot/mascot/" + client_mascota.getId(), Mascota.class);

        //ResponseEntity<Client> clientEntity = restTemplate.getForEntity("http://client_service/api/cliente/client/" + client_mascota.getIdCliente(), Client.class);
        //ResponseEntity<Mascota> mascotaEntity = restTemplate.getForEntity("http://mascot_service/api/mascot/mascot/" + client_mascota.getId(), Mascota.class);

        if (clientEntity == null || mascotaEntity == null){
            return null;
        }

        client_mascota.setNombre(clientEntity.getBody().getNombre());
        client_mascota.setDireccion(clientEntity.getBody().getDireccion());
        client_mascota.setNumeroCelular(clientEntity.getBody().getNumeroCelular());
        client_mascota.setEmail(clientEntity.getBody().getEmail());

        client_mascota.setName(mascotaEntity.getBody().getName());
        client_mascota.setAnimal(mascotaEntity.getBody().getAnimal());
        client_mascota.setRace(mascotaEntity.getBody().getRace());
        client_mascota.setAge(mascotaEntity.getBody().getAge());

        Client_Mascota entidad = clienteMascotaRepository.save(client_mascota);
        return entidad;
    }


    @DeleteMapping("/clientMascota/{id}")
    public void eliminar(@RequestParam("id") Integer id) {
        this.clienteMascotaRepository.deleteById(id);
    }

}
