package com.b2vnradarapi.b2vnradarapi.modules.radar.service;

import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarContagemResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.ContagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContagemService {

    @Autowired
    private ContagensRepository contagensRepository;
    @Autowired
    private RadarService radarService;

    public RadarContagemResponse buscarFluxoVeiculos(Integer codigo) {
        var radar = radarService.buscarPorCodigo(codigo.toString());
        var response = contagensRepository.findFluxoVeiculosByCodigo(codigo);
        response.setBaseRadares(radar);
        return response;
    }

    public RadarContagemResponse buscarFluxoVeiculosPorDataHora(Integer codigo,
                                                                LocalDateTime dataHoraInicial,
                                                                LocalDateTime dataHoraFinal) {
        var radar = radarService.buscarPorCodigo(codigo.toString());
        var response = contagensRepository
            .findFluxoVeiculosByCodigoAndDataHora(codigo, dataHoraInicial, dataHoraFinal);
        response.setBaseRadares(radar);
        return response;
    }
}
