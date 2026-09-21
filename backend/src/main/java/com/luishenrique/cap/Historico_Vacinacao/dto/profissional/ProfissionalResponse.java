package com.luishenrique.cap.Historico_Vacinacao.dto.profissional;

import com.luishenrique.cap.Historico_Vacinacao.database.models.ENUM.TipoProfissional;
import com.luishenrique.cap.Historico_Vacinacao.dto.unidade.UnidadeResponse;

import java.time.LocalDate;

public record ProfissionalResponse(
   Long id,
   String nome,
   LocalDate dataNascimento,
   String documento,
   String cargo,
   TipoProfissional tipoProf,
   Boolean ativo,
   UnidadeResponse unidade
) {}
