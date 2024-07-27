package br.com.fiap.g60.health.med.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    String crm;
    private String telefone;
    private String email;
    private String endereco;
    private String cidade;
    private String estado;
    private String especialidades;
    private Double valorConsulta;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Medico(UUID medicoId) {
        this.id = medicoId;
    }
}
