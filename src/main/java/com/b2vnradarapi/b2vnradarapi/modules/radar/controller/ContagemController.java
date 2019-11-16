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
import java.time.LocalDateTime;

@RadaresBaseUrl
public class ContagemController {

    @Autowired
    private ContagemService contagemService;

    @GetMapping("contagens/fluxo-veiculo/{codigoRadar}")
    public RadarContagemResponse buscarFluxoVeiculosPorCodigoRadar(@PathVariable Integer codigoRadar) {
        return contagemService.buscarFluxoVeiculos(codigoRadar);
    }

    @GetMapping("contagens/fluxo-veiculo")
    public RadarContagemResponse buscarFluxoVeiculosPorCodigoRadar(@PathParam("codigoRadar") Integer codigoRadar,
                                                                   @PathParam("dataInicial") LocalDateTime dataInicial,
                                                                   @PathParam("dataFinal") LocalDateTime dataFinal) {
        return contagemService.buscarFluxoVeiculosPorDataHora(codigoRadar, dataInicial, dataFinal);
    }

    @GetMapping("/contagens/infracoes-totais")
    public Page<ContagensInfracoesResponse> buscarTodasAsInfracoesPorRadares(@PathParam("page") Integer page,
                                                                             @PathParam("size") Integer size) {
        return contagemService.buscarInfracoesPorRadares(page, size);
    }

    @GetMapping("/contagens/infracoes/radar/{codigoRadar}")
    public ContagensInfracoesResponse buscarTodasAsInfracoesPorRadares(@PathVariable Integer codigoRadar) {
        return contagemService.buscarInfracoesPorRadar(codigoRadar);
    }

    @GetMapping("/contagens/acuracia-totais")
    public Page<ContagensAcuraciaResponse> buscarTodasAsAcuraciasPorRadares(@PathParam("page") Integer page,
                                                                            @PathParam("size") Integer size) {
        return contagemService.buscarAcuraciaPorRadares(page, size);
    }

    @GetMapping("/contagens/acuracia/radar/{codigoRadar}")
    public ContagensAcuraciaResponse buscarTodasAsAcuraciasPorRadares(@PathVariable Integer codigoRadar) {
        return contagemService.buscarAcuarciaPorRadar(codigoRadar);
    }
}
