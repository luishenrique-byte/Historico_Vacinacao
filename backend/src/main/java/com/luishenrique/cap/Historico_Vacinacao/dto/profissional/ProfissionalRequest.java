package com.luishenrique.cap.Historico_Vacinacao.dto.profissional;

import com.luishenrique.cap.Historico_Vacinacao.database.models.ENUM.TipoProfissional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProfissionalRequest(
        @NotBlank String nome,
        @NotBlank LocalDate dataNascimento,
        @NotBlank String documento,
        @NotBlank String cargo,
        @NotNull TipoProfissional tipoProf,
        @NotNull Long idUnidade
) {}