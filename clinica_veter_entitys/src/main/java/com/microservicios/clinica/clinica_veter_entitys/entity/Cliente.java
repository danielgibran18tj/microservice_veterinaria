package com.microservicios.clinica.clinica_veter_entitys.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @Column(name = "id_cliente", nullable = false, length = 15)
    private Integer idCliente;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(name = "numero_celular", nullable = false,length = 50)
    private String numeroCelular;

    @Column(length = 50)
    private String email;

    // Relación 1 a muchos con Mascota
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Mascota> idMascota;
}
