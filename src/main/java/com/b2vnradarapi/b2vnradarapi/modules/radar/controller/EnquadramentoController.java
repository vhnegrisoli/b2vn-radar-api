package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.service.EnquadramentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RadaresBaseUrl
public class EnquadramentoController {

    @Autowired
    private EnquadramentoService enquadramentoService;

    @GetMapping(value = "enquadramento/{enquadramento}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public Page<BaseRadares> buscarPorEnquadramentos(@PathVariable String enquadramento,
                                                     @PathParam("page") Integer page,
                                                     @PathParam("size") Integer size) {
        return enquadramentoService.buscarPorEnquadramento(enquadramento, page, size);
    }

    @GetMapping(value = "enquadramentos", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<String> buscarEnquadramentos() {
        return enquadramentoService.buscarEnquadramentos();
    }

}
