package br.com.fiap.g60.health.med.domain;

import br.com.fiap.g60.health.med.domain.enums.TipoUsuario;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;


}
