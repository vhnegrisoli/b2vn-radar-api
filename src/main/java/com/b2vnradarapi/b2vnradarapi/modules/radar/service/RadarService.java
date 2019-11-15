package com.b2vnradarapi.b2vnradarapi.modules.radar.service;

import com.b2vnradarapi.b2vnradarapi.config.exception.ValidacaoException;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.Contagens;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.BaseRadaresRepository;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.ContagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public BaseRadares buscarPorId(Integer id) {
        return baseRadaresRepository.findById(id)
            .orElseThrow(() -> RADAR_NAO_ENCONTRADO);
    }

    public BaseRadares buscarPorCodigo(String codigo) {
        return baseRadaresRepository.findByCodigoIgnoreCaseContaining(codigo)
            .orElseThrow(() -> RADAR_NAO_ENCONTRADO);
    }

    public List<BaseRadares> buscarPorTipos(String tipo, Integer page, Integer size) {
        var pageRequest = PageRequest.of(page, size);
        var tipos = contagensRepository.findByTipo(0, pageRequest);
        return baseRadaresRepository
            .findByIdIn(tipos
                    .getContent()
                    .stream()
                .map(Contagens::getLocalidade)
                .collect(Collectors.toList())
            );
    }
}