package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.ContagensAcuraciaResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.ContagensInfracoesResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarContagemResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.service.ContagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RadaresBaseUrl
public class ContagemController {

    @Autowired
    private ContagemService contagemService;

    @GetMapping(value = "contagens/fluxo-veiculo/{codigoRadar}",
        produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public RadarContagemResponse buscarFluxoVeiculosPorCodigoRadar(@PathVariable Integer codigoRadar) {
        return contagemService.buscarFluxoVeiculos(codigoRadar);
    }

    @GetMapping(value = "/contagens/infracoes-totais", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public Page<ContagensInfracoesResponse> buscarTodasAsInfracoesPorRadares(@PathParam("page") Integer page,
                                                                             @PathParam("size") Integer size) {
        return contagemService.buscarInfracoesPorRadares(page, size);
    }

    @GetMapping(value = "/contagens/infracoes/radar/{codigoRadar}",
        produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ContagensInfracoesResponse buscarTodasAsInfracoesPorRadares(@PathVariable Integer codigoRadar) {
        return contagemService.buscarInfracoesPorRadar(codigoRadar);
    }

    @GetMapping(value = "/contagens/acuracia-totais", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public Page<ContagensAcuraciaResponse> buscarTodasAsAcuraciasPorRadares(@PathParam("page") Integer page,
                                                                            @PathParam("size") Integer size) {
        return contagemService.buscarAcuraciaPorRadares(page, size);
    }

    @GetMapping(value = "/contagens/acuracia/radar/{codigoRadar}",
        produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ContagensAcuraciaResponse buscarTodasAsAcuraciasPorRadares(@PathVariable Integer codigoRadar) {
        return contagemService.buscarAcuarciaPorRadar(codigoRadar);
    }
}
