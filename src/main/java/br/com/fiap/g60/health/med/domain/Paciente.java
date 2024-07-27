package br.com.fiap.g60.health.med.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private String sexo;
    private String cidade;
    private String estado;
}
