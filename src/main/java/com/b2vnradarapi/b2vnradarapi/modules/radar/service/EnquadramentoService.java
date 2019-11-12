package com.b2vnradarapi.b2vnradarapi.modules.radar.service;

import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.BaseRadaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquadramentoService {

    @Autowired
    private BaseRadaresRepository baseRadaresRepository;

    public List<String> buscarEnquadramentos() {
        return baseRadaresRepository.findEnquadramentoDistict();
    }

    public List<BaseRadares> buscarPorEnquadramento(String enquadramento) {
        return baseRadaresRepository.findByEnquadrame(enquadramento);
    }
}
