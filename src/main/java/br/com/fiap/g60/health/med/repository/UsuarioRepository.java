package br.com.fiap.g60.health.med.repository;

import br.com.fiap.g60.health.med.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
