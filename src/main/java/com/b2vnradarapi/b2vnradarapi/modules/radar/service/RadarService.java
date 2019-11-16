package com.b2vnradarapi.b2vnradarapi.modules.radar.service;

import com.b2vnradarapi.b2vnradarapi.config.exception.ValidacaoException;
import com.b2vnradarapi.b2vnradarapi.modules.acidentes.service.TipoVeiculoService;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadaresVelocidadeResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.TiposPorRadarResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.TiposRadarTotais;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.Contagens;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.BaseRadaresRepository;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.ContagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RadarService {

    private static final  ValidacaoException RADAR_NAO_ENCONTRADO =
        new ValidacaoException("O radar não foi encontrado");

    @Autowired
    private BaseRadaresRepository baseRadaresRepository;
    @Autowired
    private ContagensRepository contagensRepository;
    @Autowired
    private TipoVeiculoService tipoVeiculoService;

    public BaseRadares buscarPorId(Integer id) {
        return baseRadaresRepository.findById(id)
            .orElseThrow(() -> RADAR_NAO_ENCONTRADO);
    }

    public BaseRadares buscarPorCodigo(String codigo) {
        return baseRadaresRepository.findByCodigoIgnoreCaseContaining(codigo)
            .orElseThrow(() -> RADAR_NAO_ENCONTRADO);
    }

    public List<BaseRadares> buscarPorTipos(String tipo) {
        var tipoVeiculo = tipoVeiculoService.buscarPorVeiculos(tipo);
        var tiposEncontrados = contagensRepository.findByTipo(tipoVeiculo.getTipo());
        var codigos = tiposEncontrados
            .stream()
            .map(Contagens::getLocalidade)
            .collect(Collectors.toList());
        var lista = new HashSet<String>();
        codigos.forEach(item -> lista.add(item.toString()));
        return baseRadaresRepository.findByCodigoIn(lista);
    }

    public List<TiposPorRadarResponse> buscarTiposPorRadar(Integer codigoRadar) {
        return contagensRepository.findTiposPorRadar(codigoRadar);
    }

    public Page<TiposPorRadarResponse> buscarTiposPorRadarPaginado(Integer page, Integer size) {
        var pageRequest = PageRequest.of(page, size);
        var totais = contagensRepository.findTiposPorRadares();
        Page<TiposPorRadarResponse> tiposPaginados =
            new PageImpl<TiposPorRadarResponse>(totais, pageRequest, totais.size());
        return tiposPaginados;
    }

    public List<TiposRadarTotais> buscarTiposTotais() {
        return contagensRepository.findTipos();
    }

    public List<String> buscarTodasVelocidades() {
        return baseRadaresRepository.findVelocidadeDistinct();
    }

    public Page<BaseRadares> buscarTodosOsRadaresPorVelocidade(Integer velocidade, Integer page, Integer size) {
        var pageRequest = PageRequest.of(page, size);
        var listaRadares = baseRadaresRepository.findRadaresByVelocidade(velocidade);
        return new PageImpl<BaseRadares>(listaRadares, pageRequest, listaRadares.size());
    }

    public List<RadaresVelocidadeResponse> buscarTodasVelocidadesPorRadares() {
        return  baseRadaresRepository.findTotalRadaresByVelocidade();
    }
}