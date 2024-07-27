package br.com.fiap.g60.health.med.service;

import br.com.fiap.g60.health.med.domain.Medico;
import br.com.fiap.g60.health.med.exception.ResourceNotFoundException;
import br.com.fiap.g60.health.med.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;

    public List<Medico> list() {
        return medicoRepository.findAll();
    }

    public Medico load(UUID id) {
        return medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
    }

    public Medico criarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico update(UUID id, Medico updt) {
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));

        medico.setNome(updt.getNome());
        medico.setEspecialidades(updt.getEspecialidades());
        medico.setCrm(updt.getCrm());
        medico.setTelefone(updt.getTelefone());
        medico.setEmail(updt.getEmail());
        medico.setTelefone(updt.getTelefone());
        medico.setValorConsulta(updt.getValorConsulta());

        // TODO: chamar API do google places passando dados de endereço para calcular latitude e longitude
        medico.setEndereco(updt.getEndereco());
        medico.setCidade(updt.getCidade());
        medico.setEstado(updt.getEstado());


        return medicoRepository.save(medico);
    }

    public void delete(UUID id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        medicoRepository.delete(medico);
    }
}
