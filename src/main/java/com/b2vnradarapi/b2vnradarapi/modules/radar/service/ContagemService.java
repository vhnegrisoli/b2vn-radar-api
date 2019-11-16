package com.b2vnradarapi.b2vnradarapi.modules.radar.service;

import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.ContagensInfracoesResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarContagemResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.ContagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    public Page<ContagensInfracoesResponse> buscarInfracoesPorRadares(Integer page, Integer size) {
        var pageRequest = PageRequest.of(page, size);
        var totais = contagensRepository.findAutuacoesRadares();
        return new PageImpl<ContagensInfracoesResponse>(totais, pageRequest, totais.size());
    }

    public ContagensInfracoesResponse buscarInfracoesPorRadar(Integer codigoRadar) {
        return contagensRepository.findAutuacoesPorRadar(codigoRadar);
    }
}
