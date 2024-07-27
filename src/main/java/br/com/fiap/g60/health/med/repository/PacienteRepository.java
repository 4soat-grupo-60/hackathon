package br.com.fiap.g60.health.med.repository;

import br.com.fiap.g60.health.med.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
}
