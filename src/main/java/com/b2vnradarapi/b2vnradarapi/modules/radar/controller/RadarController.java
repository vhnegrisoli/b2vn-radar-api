package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import com.b2vnradarapi.b2vnradarapi.modules.acidentes.enums.ETipoVeiculo;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadaresVelocidadeResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.TiposPorRadarResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.TiposRadarTotais;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.service.RadarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RadaresBaseUrl
public class RadarController {

    @Autowired
    private RadarService radarService;

    @GetMapping(value = "{id}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public BaseRadares buscarPorId(@PathVariable Integer id) {
        return radarService.buscarPorId(id);
    }

    @GetMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public BaseRadares buscarUmRadar(@PathParam("codigo") String codigo) {
        return radarService.buscarPorCodigo(codigo);
    }

    @GetMapping(value = "/tipo/{tipo}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public Page<BaseRadares> buscarRadarPorTipo(@PathVariable ETipoVeiculo tipo,
                                                @PathParam("page") Integer page,
                                                @PathParam("size") Integer size) {
        return radarService.buscarPorTipos(tipo, page, size);
    }

    @GetMapping(value = "{codigoRadar}/tipo", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<TiposPorRadarResponse> buscarTipos(@PathVariable Integer codigoRadar) {
        return radarService.buscarTiposPorRadar(codigoRadar);
    }

    @GetMapping(value = "tipo/totais", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<TiposRadarTotais> buscarTotaisTipos() {
        return radarService.buscarTiposTotais();
    }

    @GetMapping(value = "tipo/totais/page", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public Page<TiposPorRadarResponse> buscarTotaisTiposPaginado(@PathParam("page") Integer page,
                                                  @PathParam("size") Integer size) {
        return radarService.buscarTiposPorRadarPaginado(page, size);
    }

    @GetMapping(value = "velocidades", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<String> buscarTodasVelocidades(@PathParam("page") Integer page,
                                               @PathParam("size") Integer size) {
        return radarService.buscarTodasVelocidades();
    }

    @GetMapping(value = "velocidades-por-radares", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<RadaresVelocidadeResponse> buscarTodosRadaresPorVelocidade() {
        return radarService.buscarTodasVelocidadesPorRadares();
    }

    @GetMapping(value = "velocidade/{velocidade}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public Page<BaseRadares> buscarRadaresPorVelocidade(@PathVariable Integer velocidade,
                                                        @PathParam("page") Integer page,
                                                        @PathParam("size") Integer size) {
        return radarService.buscarTodosOsRadaresPorVelocidade(velocidade, page, size);
    }

    @GetMapping(value = "buscar-todos", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public Page<BaseRadares> buscarTodosPaginado(@PathParam("page") Integer page,
                                                 @PathParam("size") Integer size) {
        return radarService.buscarRadaresPaginados(page, size);
    }
}
