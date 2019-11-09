package com.b2vnradarapi.b2vnradarapi.modules.radar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "viagens")
public class Viagens {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "INICIO")
    @NotNull
    private Integer inicio;

    @Column(name = "DATA_INICIO")
    @NotNull
    private Integer dataInicio;

    @Column(name = "FINAL")
    @NotNull
    private Integer finalViagem;

    @Column(name = "DATA_FINAL")
    @NotNull
    private Integer dataFinal;

    @Column(name = "TIPO")
    @NotNull
    private Integer tipo;
}
