package com.luishenrique.cap.Historico_Vacinacao.database.models;

import com.luishenrique.cap.Historico_Vacinacao.database.models.ENUM.TipoProfissional;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "profissional")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfissionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 14)
    private String documento;

    @Column(nullable = false)
    private String cargo;

    @Column(name = "tipo", nullable = false)
    private TipoProfissional tipoProf;

    @ManyToOne
    @JoinColumn(name = "id_unidade")
    private UnidadeAtendimentoEntity unidade;

    @Builder.Default
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean ativo = true;
}
