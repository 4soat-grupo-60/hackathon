package br.com.fiap.g60.health.med.controller;

import br.com.fiap.g60.health.med.domain.Medico;
import br.com.fiap.g60.health.med.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("medico")
@RestController
@Secured("ROLE_USER")
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> list() {
        return ResponseEntity.ok(medicoService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> load(@PathVariable UUID id) {
        return ResponseEntity.ok(medicoService.load(id));
    }

    @PostMapping
    public ResponseEntity<Medico> save(@RequestBody Medico medico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.criarMedico(medico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizarMedico(@PathVariable UUID id, @RequestBody Medico updt) {
        return ResponseEntity.ok(medicoService.update(id, updt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable UUID id) {
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
