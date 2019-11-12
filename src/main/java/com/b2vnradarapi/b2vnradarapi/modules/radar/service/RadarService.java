package com.b2vnradarapi.b2vnradarapi.modules.radar.service;

import com.b2vnradarapi.b2vnradarapi.config.exception.ValidacaoException;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.BaseRadaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RadarService {

    private static final  ValidacaoException RADAR_NAO_ENCONTRADO =
        new ValidacaoException("O radar não foi encontrado");

    @Autowired
    private BaseRadaresRepository baseRadaresRepository;

    public BaseRadares buscarPorId(Integer id) {
        return baseRadaresRepository.findById(id)
            .orElseThrow(() -> RADAR_NAO_ENCONTRADO);
    }

    public BaseRadares buscarPorCodigo(String codigo) {
        return baseRadaresRepository.findByCodigoIgnoreCaseContaining(codigo)
            .orElseThrow(() -> RADAR_NAO_ENCONTRADO);
    }
}