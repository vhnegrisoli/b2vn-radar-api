package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.service.EnquadramentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RadaresBaseUrl
public class EnquadramentoController {

    @Autowired
    private EnquadramentoService enquadramentoService;

    @GetMapping("enquadramento/{enquadramento}")
    public List<BaseRadares> buscarPorEnquadramentos(@PathVariable String enquadramento) {
        return enquadramentoService.buscarPorEnquadramento(enquadramento);
    }

    @GetMapping("enquadramentos")
    public List<String> buscarEnquadramentos() {
        return enquadramentoService.buscarEnquadramentos();
    }

}
