package com.b2vnradarapi.b2vnradarapi.modules.trajetos.controller;

import com.b2vnradarapi.b2vnradarapi.modules.trajetos.dto.TrajetoDistanciaResponse;
import com.b2vnradarapi.b2vnradarapi.modules.trajetos.dto.TrajetosPageResponse;
import com.b2vnradarapi.b2vnradarapi.modules.trajetos.dto.TrajetosVelocidadePageResponse;
import com.b2vnradarapi.b2vnradarapi.modules.trajetos.service.TrajetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping("/api/trajetos")
public class TrajetosController {

    @Autowired
    private TrajetoService trajetoService;

    @GetMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public TrajetosPageResponse buscarPorOrigensDestinos(@PathParam("page") Integer page,
                                                         @PathParam("size") Integer size) {
        return trajetoService.buscarTodosOsTrajetos(page, size);
    }

    @GetMapping(value = "velocidades",produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public TrajetosVelocidadePageResponse buscarPorVelocidades(@PathParam("page") Integer page,
                                                               @PathParam("size") Integer size) {
        return trajetoService.buscarVelocidadesMediasDosTrajetos(page, size);
    }

    @GetMapping(value = "{id}/distancia", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public TrajetoDistanciaResponse buscarDistanciaPorTrajeto(@PathVariable Integer id) {
        return trajetoService.buscarDistanciaPeloTrajeto(id);
    }
}
