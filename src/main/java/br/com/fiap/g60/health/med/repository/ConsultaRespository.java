package br.com.fiap.g60.health.med.repository;

import br.com.fiap.g60.health.med.domain.Consulta;
import br.com.fiap.g60.health.med.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ConsultaRespository extends JpaRepository<Consulta, UUID> {


    @Query("SELECT c.inicio FROM Consulta c WHERE c.medico.id = :medicoId AND c.inicio BETWEEN :ini AND :fim")
    List<LocalDateTime> findHorariosAgendados(@Param("medicoId") UUID medico,
                                              @Param("ini") LocalDateTime ini,
                                              @Param("fim") LocalDateTime fim);

}
