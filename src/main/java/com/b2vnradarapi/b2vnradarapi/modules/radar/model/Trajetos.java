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
@Table(name = "trajetos")
public class Trajetos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "VIAGEM_ID")
    @NotNull
    private Integer viagemId;

    @Column(name = "TIPO")
    @NotNull
    private Integer tipo;

    @Column(name = "DATA_INICIO")
    @NotNull
    private Integer dataInicio;

    @Column(name = "DATA_FINAL")
    @NotNull
    private Integer dataFinal;

    @Column(name = "ORIGEM")
    @NotNull
    private Integer origem;

    @Column(name = "DESTINO")
    @NotNull
    private Integer destino;

    @Column(name = "V0")
    @NotNull
    private Integer v0;

    @Column(name = "V1")
    @NotNull
    private Integer v1;
}
