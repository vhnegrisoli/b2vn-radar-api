package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.service.RadarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;

@RadaresBaseUrl
public class RadarController {

    @Autowired
    private RadarService radarService;

    @GetMapping("{id}")
    public BaseRadares buscarPorId(@PathVariable Integer id) {
        return radarService.buscarPorId(id);
    }

    @GetMapping
    public BaseRadares buscarUmRadar(@PathParam("codigo") String codigo) {
        return radarService.buscarPorCodigo(codigo);
    }

    @GetMapping("/tipo/{tipo}")
    public List<BaseRadares> buscarRadarPorTipo(@PathVariable String tipo,
                                                @PathParam("page") Integer page,
                                                @PathParam("size") Integer size) {
        return radarService.buscarPorTipos(tipo, page, size);
    }
}
