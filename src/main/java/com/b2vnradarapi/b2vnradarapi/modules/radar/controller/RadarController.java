package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import com.b2vnradarapi.b2vnradarapi.config.exception.ValidacaoException;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.Contagens;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.Trajetos;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.Viagens;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.BaseRadaresRepository;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.ContagensRepository;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.TrajetosRepository;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.ViagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/radares")
public class RadarController {

    @Autowired
    private BaseRadaresRepository baseRadaresRepository;
    @Autowired
    private ContagensRepository contagensRepository;
    @Autowired
    private TrajetosRepository trajetosRepository;
    @Autowired
    private ViagensRepository viagensRepository;

    @GetMapping("radar/{id}")
    public BaseRadares buscarUmRadar(@PathVariable Integer id) {
        return baseRadaresRepository.findById(id).orElseThrow(() -> new ValidacaoException("Não encontrado"));
    }

    @GetMapping("contagens/{id}")
    public Contagens buscarUmaContagem(@PathVariable Integer id) {
        return contagensRepository.findById(id).orElseThrow(() -> new ValidacaoException("Não encontrado"));
    }

    @GetMapping("trajetos/{id}")
    public Trajetos buscarUmTrajeto(@PathVariable Integer id) {
        return trajetosRepository.findById(id).orElseThrow(() -> new ValidacaoException("Não encontrado"));
    }

    @GetMapping("viagens/{id}")
    public Viagens buscarUmaViagem(@PathVariable Integer id) {
        return viagensRepository.findById(id).orElseThrow(() -> new ValidacaoException("Não encontrado"));
    }
}
