package com.b2vnradarapi.b2vnradarapi.modules.radar.service;

import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.BaseRadaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcessaoService {

    @Autowired
    private BaseRadaresRepository baseRadaresRepository;

    public List<BaseRadares> buscarPorLote(Integer lote) {
        return baseRadaresRepository.findByLote(lote);
    }

    public List<Integer> buscarLotes() {
        return baseRadaresRepository.findLoteDistict();
    }
}
