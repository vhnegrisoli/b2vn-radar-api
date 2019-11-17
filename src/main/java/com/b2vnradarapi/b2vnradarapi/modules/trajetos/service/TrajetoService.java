package com.b2vnradarapi.b2vnradarapi.modules.trajetos.service;

import com.b2vnradarapi.b2vnradarapi.config.exception.ValidacaoException;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarLocalizacaoResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.BaseRadaresRepository;
import com.b2vnradarapi.b2vnradarapi.modules.trajetos.dto.TrajetoDistanciaResponse;
import com.b2vnradarapi.b2vnradarapi.modules.trajetos.dto.TrajetosResponse;
import com.b2vnradarapi.b2vnradarapi.modules.trajetos.dto.TrajetosVelocidadesMediasResponse;
import com.b2vnradarapi.b2vnradarapi.modules.trajetos.repository.TrajetosRepository;
import com.b2vnradarapi.b2vnradarapi.modules.trajetos.utils.TrajetoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TrajetoService {

    @Autowired
    private TrajetosRepository trajetosRepository;
    @Autowired
    private BaseRadaresRepository baseRadaresRepository;

    public Page<TrajetosResponse> buscarTodosOsTrajetos(Integer page, Integer size) {
        var pageRequest = PageRequest.of(page, size);
        var trajetosPaginados = trajetosRepository.findAll(pageRequest);
        var response = new ArrayList<TrajetosResponse>();
        trajetosPaginados.forEach(item -> {
            response.add(
                new TrajetosResponse(item,
                    buscarRadar(item.getOrigem().toString()),
                    buscarRadar(item.getDestino().toString())));
        });
        return new PageImpl<TrajetosResponse>(response, pageRequest, response.size());
    }

    public Page<TrajetosVelocidadesMediasResponse> buscarVelocidadesMediasDosTrajetos(Integer page, Integer size) {
        var pageRequest = PageRequest.of(page, size);
        var trajetosPaginados = trajetosRepository.findAll(pageRequest);
        var response = new ArrayList<TrajetosVelocidadesMediasResponse>();
        trajetosPaginados.forEach(item -> {
            response.add(new TrajetosVelocidadesMediasResponse(
                new TrajetosResponse(item,
                    buscarRadar(item.getOrigem().toString()),
                    buscarRadar(item.getDestino().toString()))));
        });
        return new PageImpl<TrajetosVelocidadesMediasResponse>(response, pageRequest, response.size());
    }

    private Optional<BaseRadares> buscarRadar(String codigoRadar) {
        return baseRadaresRepository.findByCodigoIgnoreCaseContaining(codigoRadar);
    }

    public TrajetoDistanciaResponse buscarDistanciaPeloTrajeto(Integer id) {
        var trajeto = trajetosRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("Trajeto não encontrado."));
        var radarOrigem = buscarRadar(trajeto.getOrigem().toString());
        var radarDestino = buscarRadar(trajeto.getDestino().toString());
        validarRadaresOrigemDestino(radarOrigem, radarDestino);
        var localizacoesOrigem =  RadarLocalizacaoResponse.of(radarOrigem.get());
        var localizacoesDestino =  RadarLocalizacaoResponse.of(radarDestino.get());
        return TrajetoDistanciaResponse
            .builder()
            .trajeto(trajeto)
            .distancia(TrajetoUtils.calcularDistancia(
                localizacoesOrigem.getLatitude(), localizacoesOrigem.getLongitude(),
                localizacoesDestino.getLatitude(), localizacoesDestino.getLongitude()))
            .build();
    }

    private void validarRadaresOrigemDestino(Optional<BaseRadares> radarOrigem, Optional<BaseRadares> radarDestino) {
        if (radarOrigem.isEmpty()) {
            throw new ValidacaoException("Não é possível calcular a distância pois o radar de origem "
                + "não foi encontrado.");
        }
        if (radarDestino.isEmpty()) {
            throw new ValidacaoException("Não é possível calcular a distância pois o radar de destino "
                + "não foi encontrado.");
        }
    }
}