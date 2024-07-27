package br.com.fiap.g60.health.med.controller;

import br.com.fiap.g60.health.med.domain.Agenda;
import br.com.fiap.g60.health.med.domain.HorarioAtendimento;
import br.com.fiap.g60.health.med.domain.dto.HorarioItem;
import br.com.fiap.g60.health.med.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("agenda")
@RestController
public class AgendaController {
    private final AgendaService agendaService;


    @GetMapping("/get/{medicoId}")
    @Secured("ROLE_MEDICO")
    public ResponseEntity<Agenda> listByMedico(@PathVariable UUID medicoId) {
        var agendas = agendaService.getByMedico(medicoId);
        return ResponseEntity.ok(agendas);
    }

    @GetMapping("/disponiveis/medico/{medicoId}")
    @Secured("ROLE_USER")
    public ResponseEntity<List<HorarioItem>> getHorariosDisponiveis(@PathVariable UUID medicoId, @RequestParam String data) {
        LocalDate localDate = LocalDate.parse(data);
        List<HorarioItem> horariosDisponiveis = agendaService.getHorariosDisponiveis(medicoId, localDate);
        return ResponseEntity.ok(horariosDisponiveis);
    }

    @PostMapping("/save/{medicoId}")
    @Secured("ROLE_MEDICO")
    public ResponseEntity<Agenda> saveAgenda(@PathVariable UUID medicoId,
                                             @RequestBody List<HorarioAtendimento> horarios) {
        var agenda = agendaService.save(medicoId, horarios);
        return ResponseEntity.ok(agenda);
    }

    @PutMapping("/update/{medicoId}")
    @Secured("ROLE_MEDICO")
    public ResponseEntity<Void> updateAgenda(@PathVariable UUID medicoId,
                                             @RequestBody List<HorarioAtendimento> horarios) {
        agendaService.update(medicoId, horarios);
        return ResponseEntity.noContent().build();
    }

}
