package br.com.fiap.g60.health.med.service;

import br.com.fiap.g60.health.med.domain.Agenda;
import br.com.fiap.g60.health.med.domain.HorarioAtendimento;
import br.com.fiap.g60.health.med.domain.Medico;
import br.com.fiap.g60.health.med.domain.dto.HorarioItem;
import br.com.fiap.g60.health.med.domain.enums.TipoHorario;
import br.com.fiap.g60.health.med.repository.AgendaRepository;
import br.com.fiap.g60.health.med.repository.ConsultaRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final ConsultaRespository consultaRespository;

    public List<HorarioItem> getHorariosDisponiveis(UUID medicoId, LocalDate data) {

        Agenda agenda = getAgendaMedico(medicoId);

        if (!agenda.isAtivo()) return Collections.emptyList();

        // Filtra os horários especiais para a data especificada
        List<HorarioAtendimento> horariosAtendimento = agenda.getHorariosAtendimento().stream()
                .filter(horario -> {
                    if (horario.getTipo() == TipoHorario.ESPECIAL) {
                        return horario.getData().equals(data);
                    } else {
                        return horario.getDiaSemana() == data.getDayOfWeek();
                    }
                })
                .toList();

        // Busca as consultas agendadas para o médico no dia especificado
        Set<LocalTime> horariosOcupados = getHorariosOcupados(medicoId, data);

        // Coleta os horários disponíveis
        return horariosAtendimento.stream()
                .filter(horario -> !horariosOcupados.contains(horario.getHoraInicio()))
                .map((horario) -> new HorarioItem(horario.getHoraInicio(), horario.getHoraFim()))
                .distinct()
                .collect(Collectors.toList());
    }

    public Agenda save(UUID medicoId, List<HorarioAtendimento> horariosAtendimento) {
        Agenda agenda = agendaRepository.findByMedico_Id(medicoId).orElse(new Agenda());
        agenda.setMedico(new Medico(medicoId));
        agenda.setHorariosAtendimento(horariosAtendimento);
        agenda.setAtivo(true);
        return agendaRepository.save(agenda);
    }

    public void update(UUID medicoId, List<HorarioAtendimento> horariosAtendimento) {
        Agenda agenda = getAgendaMedico(medicoId);
        agenda.setHorariosAtendimento(horariosAtendimento);
    }

    public Agenda getByMedico(UUID medicoId) {
        return getAgendaMedico(medicoId);
    }

    private Set<LocalTime> getHorariosOcupados(UUID medicoId, LocalDate data) {
        LocalDateTime startOfDay = data.atStartOfDay();
        LocalDateTime endOfDay = data.atTime(LocalTime.MAX);

        return consultaRespository
                .findHorariosAgendados(medicoId, startOfDay, endOfDay)
                .stream().map(LocalDateTime::toLocalTime)
                .collect(Collectors.toSet());
    }

    private Agenda getAgendaMedico(UUID medicoId) {
        return agendaRepository.findByMedico_Id(medicoId).orElseThrow(() -> new RuntimeException("No agenda found for the given medico"));
    }
}
