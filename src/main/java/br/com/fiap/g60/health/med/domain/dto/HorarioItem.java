package br.com.fiap.g60.health.med.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HorarioItem {

    private LocalTime inicio;
    private LocalTime fim;

}
