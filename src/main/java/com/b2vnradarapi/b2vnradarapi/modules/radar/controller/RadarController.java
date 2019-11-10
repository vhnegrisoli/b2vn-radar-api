package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarContagemResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.service.RadarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/radares")
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

    @GetMapping("localizacao")
    public BaseRadares buscarPorLocalizacao(@PathParam("latitude_longitude") String latitudeLongitude) {
        return radarService.buscarPorLocalizacao(latitudeLongitude);
    }

    @GetMapping("localizacoes/mapa")
    public List<RadarResponse> buscarLocalizacoesMapa() {
        return radarService.buscarLocalizacoesMapa();
    }

    @GetMapping("localizacoes/mapa/concessao/{lote}")
    public List<RadarResponse> buscarLocalizacoesMapaComLote(@PathVariable Integer lote) {
        return radarService.buscarLocalizacoesMapaComLote(lote);
    }

    @GetMapping("concessoes")
    public List<Integer> buscarTodosOsLotes() {
        return radarService.buscarLotes();
    }

    @GetMapping("concessoes/{lote}")
    public List<BaseRadares> buscarPorLote(@PathVariable Integer lote) {
        return radarService.buscarPorLote(lote);
    }

    @GetMapping("localizacoes/mapa/concessoes")
    public List<RadarResponse> buscarPorLotes(@RequestParam("lotes") List<Integer> lotes) {
        return radarService.buscarPorLotes(lotes);
    }

    @GetMapping("enquadramento/{enquadramento}")
    public List<BaseRadares> buscarPorEnquadramentos(@PathVariable String enquadramento) {
        return radarService.buscarPorEnquadramento(enquadramento);
    }

    @GetMapping("enquadramentos")
    public List<String> buscarEnquadramentos() {
        return radarService.buscarEnquadramentos();
    }

    @GetMapping("localizacoes/mapa/enquadramento/{enquadramento}")
    public List<RadarResponse> buscarLocalizacoesMapaComEnquadramentos(@PathVariable String enquadramento) {
        return radarService.buscarLocalizacoesMapaComEnquadramentos(enquadramento);
    }

    @GetMapping("/contagens/fluxo-veiculo/{codigoRadar}")
    public RadarContagemResponse buscarFluxoVeiculosPorCodigoRadar(@PathVariable Integer codigoRadar) {
        return radarService.buscarFluxoVeiculos(codigoRadar);
    }

    @GetMapping("/contagens/fluxo-veiculo")
    public RadarContagemResponse buscarFluxoVeiculosPorCodigoRadar(@PathParam("codigoRadar") Integer codigoRadar,
                                                                   @PathParam("dataHoraInicial")LocalDateTime dataHoraInicial,
                                                                   @PathParam("dataHoraFinal")LocalDateTime dataHoraFinal) {
        return radarService.buscarFluxoVeiculosPorDataHora(codigoRadar, dataHoraInicial, dataHoraFinal);
    }
}
