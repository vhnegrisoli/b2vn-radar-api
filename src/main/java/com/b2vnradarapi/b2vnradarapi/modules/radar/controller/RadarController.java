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
    public Page<BaseRadares> buscarRadarPorTipo(@PathVariable ETipoVeiculo tipo,
                                                @PathParam("page") Integer page,
                                                @PathParam("size") Integer size) {
        return radarService.buscarPorTipos(tipo, page, size);
    }

    @GetMapping("{codigoRadar}/tipo")
    public List<TiposPorRadarResponse> buscarTipos(@PathVariable Integer codigoRadar) {
        return radarService.buscarTiposPorRadar(codigoRadar);
    }

    @GetMapping("tipo/totais")
    public List<TiposRadarTotais> buscarTotaisTipos() {
        return radarService.buscarTiposTotais();
    }

    @GetMapping("tipo/totais/page")
    public Page<Object> buscarTotaisTiposPaginado(@PathParam("page") Integer page,
                                                  @PathParam("size") Integer size) {
        return radarService.buscarTiposPorRadarPaginado(page, size);
    }

    @GetMapping("velocidades")
    public List<String> buscarTodasVelocidades(@PathParam("page") Integer page,
                                               @PathParam("size") Integer size) {
        return radarService.buscarTodasVelocidades();
    }

    @GetMapping("velocidades-por-radares")
    public List<RadaresVelocidadeResponse> buscarTodosRadaresPorVelocidade() {
        return radarService.buscarTodasVelocidadesPorRadares();
    }

    @GetMapping("velocidade/{velocidade}")
    public Page<BaseRadares> buscarRadaresPorVelocidade(@PathVariable Integer velocidade,
                                                        @PathParam("page") Integer page,
                                                        @PathParam("size") Integer size) {
        return radarService.buscarTodosOsRadaresPorVelocidade(velocidade, page, size);
    }

    @GetMapping("buscar-todos")
    public Page<BaseRadares> buscarTodosPaginado(@PathParam("page") Integer page,
                                                 @PathParam("size") Integer size) {
        return radarService.buscarRadaresPaginados(page, size);
    }
}
