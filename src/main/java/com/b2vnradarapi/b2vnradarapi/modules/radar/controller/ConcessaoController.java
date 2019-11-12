package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.service.ConcessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RadaresBaseUrl
public class ConcessaoController {

    @Autowired
    private ConcessaoService concessaoService;

    @GetMapping("concessoes")
    public List<Integer> buscarTodosOsLotes() {
        return concessaoService.buscarLotes();
    }

    @GetMapping("concessoes/{lote}")
    public List<BaseRadares> buscarPorLote(@PathVariable Integer lote) {
        return concessaoService.buscarPorLote(lote);
    }

}
