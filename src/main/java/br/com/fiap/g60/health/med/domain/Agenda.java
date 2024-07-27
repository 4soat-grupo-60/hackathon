package br.com.fiap.g60.health.med.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "agenda")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Medico medico;

    private boolean ativo; // Somente existirá uma agenda ativa por vez

    @ElementCollection
    @CollectionTable(name = "horario_atendimento", joinColumns = @JoinColumn(name = "agenda_id"))
    private List<HorarioAtendimento> horariosAtendimento;
}