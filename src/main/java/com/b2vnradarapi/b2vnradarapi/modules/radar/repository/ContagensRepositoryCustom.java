package com.b2vnradarapi.b2vnradarapi.modules.radar.repository;

import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarContagemResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.TiposPorRadarResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.TiposRadarTotais;

import java.time.LocalDateTime;
import java.util.List;

public interface ContagensRepositoryCustom {

    RadarContagemResponse findFluxoVeiculosByCodigo(Integer codigo);

    RadarContagemResponse findFluxoVeiculosByCodigoAndDataHora(Integer codigo, LocalDateTime dataHoraInicial,
                                                               LocalDateTime dataHoraFinal);

    List<TiposPorRadarResponse> findTiposPorRadar(Integer localidade);

    List<TiposPorRadarResponse> findTiposPorRadares();

    List<TiposRadarTotais> findTipos();
}
