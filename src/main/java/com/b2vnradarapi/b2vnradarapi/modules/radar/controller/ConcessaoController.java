package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.service.ConcessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RadaresBaseUrl
public class ConcessaoController {

    @Autowired
    private ConcessaoService concessaoService;

    @GetMapping(value = "concessoes", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<Integer> buscarTodosOsLotes() {
        return concessaoService.buscarLotes();
    }

    @GetMapping(value = "concessoes/{lote}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public Page<BaseRadares> buscarPorLote(@PathVariable Integer lote,
                                           @PathParam("page") Integer page,
                                           @PathParam("size") Integer size) {
        return concessaoService.buscarPorLote(lote, page, size);
    }
}
