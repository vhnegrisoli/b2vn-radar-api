package com.b2vnradarapi.b2vnradarapi.modules.radar.repository;

import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarContagemResponse;

import java.time.LocalDateTime;

public interface ContagensRepositoryCustom {

    RadarContagemResponse findFluxoVeiculosByCodigo(Integer codigo);

    RadarContagemResponse findFluxoVeiculosByCodigoAndDataHora(Integer codigo, LocalDateTime dataHoraInicial,
                                                               LocalDateTime dataHoraFinal);
}
