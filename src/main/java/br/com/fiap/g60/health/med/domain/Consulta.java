package br.com.fiap.g60.health.med.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Paciente paciente;

    private LocalDateTime inicio;
    private LocalDateTime termino;
    private Double total;

    private String anotacoes;
    private String link;
}
