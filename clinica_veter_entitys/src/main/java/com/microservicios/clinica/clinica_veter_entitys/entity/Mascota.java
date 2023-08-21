package com.microservicios.clinica.clinica_veter_entitys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "mascota")
@Getter
@Setter
@NoArgsConstructor
public class Mascota {
    // Relación 1 a muchos con Consulta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota", nullable = false)
    private Integer idMascota;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(length = 50)
    private String animal;

    @Column(length = 50)
    private String raza;

    @Column(nullable = false, length = 5)
    private Integer edad;

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Consulta> idConsulta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Cliente cliente;
}
