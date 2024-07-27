package br.com.fiap.g60.health.med.repository;

import br.com.fiap.g60.health.med.domain.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgendaRepository extends JpaRepository<Agenda, UUID> {


    @Query("SELECT a FROM Agenda a WHERE a.medico.id = :medicoId AND a.ativo IS TRUE")
    Optional<Agenda> findAgendaAtiva(@Param("medicoId") UUID medicoId);

    Optional<Agenda> findByMedico_Id(UUID medicoId);

}
