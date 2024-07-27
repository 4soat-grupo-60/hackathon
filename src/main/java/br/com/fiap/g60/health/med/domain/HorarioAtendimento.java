package br.com.fiap.g60.health.med.domain;

import br.com.fiap.g60.health.med.domain.enums.TipoHorario;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Embeddable
@Table(name = "horario_atendimento")
public class HorarioAtendimento {
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private boolean disponivel;     // Indica se o horário é disponível ou não

    @Enumerated(EnumType.STRING)
    private TipoHorario tipo;       // Indica se é um horário regular ou especial
    private LocalDate data;         // Usado apenas para horários especiais
    private int intervalo;          // Intervalo de atendimento em minutos
    private Double valorConsulta;
}
