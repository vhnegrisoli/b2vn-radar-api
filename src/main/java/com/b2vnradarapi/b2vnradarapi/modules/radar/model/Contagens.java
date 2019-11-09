package com.b2vnradarapi.b2vnradarapi.modules.radar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "contagens")
public class Contagens {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "DATA_E_HORA")
    @NotNull
    private LocalDateTime dataHora;

    @Column(name = "LOCALIDADE")
    @NotNull
    private Integer localidade;

    @Column(name = "TIPO")
    @NotNull
    private Integer tipo;

    @Column(name = "CONTAGEM")
    @NotNull
    private Integer contagem;

    @Column(name = "AUTUACOES")
    @NotNull
    private Integer autuacoes;

    @Column(name = "PLACAS")
    @NotNull
    private Integer placas;
}
