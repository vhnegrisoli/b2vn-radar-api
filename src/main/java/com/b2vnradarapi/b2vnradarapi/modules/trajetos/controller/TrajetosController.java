package com.b2vnradarapi.b2vnradarapi.modules.trajetos.controller;

import com.b2vnradarapi.b2vnradarapi.modules.trajetos.dto.TrajetoDistanciaResponse;
import com.b2vnradarapi.b2vnradarapi.modules.trajetos.dto.TrajetosResponse;
import com.b2vnradarapi.b2vnradarapi.modules.trajetos.dto.TrajetosVelocidadesMediasResponse;
import com.b2vnradarapi.b2vnradarapi.modules.trajetos.service.TrajetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/trajetos")
public class TrajetosController {

    @Autowired
    private TrajetoService trajetoService;

    @GetMapping
    public Page<TrajetosResponse> buscarPorOrigensDestinos(@PathParam("page") Integer page,
                                                           @PathParam("size") Integer size) {
        return trajetoService.buscarTodosOsTrajetos(page, size);
    }

    @GetMapping("velocidades")
    public Page<TrajetosVelocidadesMediasResponse> buscarPorVelocidades(@PathParam("page") Integer page,
                                                                        @PathParam("size") Integer size) {
        return trajetoService.buscarVelocidadesMediasDosTrajetos(page, size);
    }

    @GetMapping("{id}/distancia")
    public TrajetoDistanciaResponse buscarDistanciaPorTrajeto(@PathVariable Integer id) {
        return trajetoService.buscarDistanciaPeloTrajeto(id);
    }
}
