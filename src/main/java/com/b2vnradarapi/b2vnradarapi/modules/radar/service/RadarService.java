package com.b2vnradarapi.b2vnradarapi.modules.radar.service;

import com.b2vnradarapi.b2vnradarapi.config.exception.ValidacaoException;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarContagemResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.BaseRadaresRepository;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.ContagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RadarService {

    private static final  ValidacaoException RADAR_NAO_ENCONTRADO = new ValidacaoException("");

    @Autowired
    private BaseRadaresRepository baseRadaresRepository;
    @Autowired
    private ContagensRepository contagensRepository;

    public BaseRadares buscarPorId(Integer id) {
        return baseRadaresRepository.findById(id)
            .orElseThrow(() -> RADAR_NAO_ENCONTRADO);
    }

    public BaseRadares buscarPorCodigo(String codigo) {
        return baseRadaresRepository.findByCodigoIgnoreCaseContaining(codigo)
            .orElseThrow(() -> RADAR_NAO_ENCONTRADO);
    }

    public List<BaseRadares> buscarPorLote(Integer lote) {
        return baseRadaresRepository.findByLote(lote);
    }

    public List<Integer> buscarLotes() {
        return baseRadaresRepository.findLoteDistict();
    }

    public List<RadarResponse> buscarLocalizacoesMapa() {
        return baseRadaresRepository
            .findAll()
            .stream()
            .map(RadarResponse::of)
            .collect(Collectors.toList());
    }

    public List<RadarResponse> buscarLocalizacoesMapaComLote(Integer lote) {
        return baseRadaresRepository
            .findByLote(lote)
            .stream()
            .map(RadarResponse::of)
            .collect(Collectors.toList());
    }

    public List<RadarResponse> buscarPorLotes(List<Integer> lotes) {
        return baseRadaresRepository
            .findByLoteIn(lotes)
            .stream()
            .map(RadarResponse::of)
            .collect(Collectors.toList());
    }

    public BaseRadares buscarPorLocalizacao(String latitudeLongitude) {
        return baseRadaresRepository.findByLatitudeLIgnoreCaseContaining(latitudeLongitude)
            .orElseThrow(() -> new ValidacaoException("Radar não encontrado para os pontos "
            + latitudeLongitude));
    }

    public List<String> buscarEnquadramentos() {
        return baseRadaresRepository.findEnquadramentoDistict();
    }

    public List<BaseRadares> buscarPorEnquadramento(String enquadramento) {
        return baseRadaresRepository.findByEnquadrame(enquadramento);
    }

    public List<RadarResponse> buscarLocalizacoesMapaComEnquadramentos(String enquadramento) {
        return baseRadaresRepository.findByEnquadrame(enquadramento)
            .stream()
            .map(RadarResponse::of)
            .collect(Collectors.toList());
    }

    public RadarContagemResponse buscarFluxoVeiculos(Integer codigo) {
        var radar = buscarPorCodigo(codigo.toString());
        var response = contagensRepository.findFluxoVeiculosByCodigo(codigo);
        response.setBaseRadares(radar);
        return response;
    }

    public RadarContagemResponse buscarFluxoVeiculosPorDataHora(Integer codigo,
                                                                LocalDateTime dataHoraInicial,
                                                                LocalDateTime dataHoraFinal) {
        var radar = buscarPorCodigo(codigo.toString());
        var response = contagensRepository
            .findFluxoVeiculosByCodigoAndDataHora(codigo, dataHoraInicial, dataHoraFinal);
        response.setBaseRadares(radar);
        return response;
    }
}
