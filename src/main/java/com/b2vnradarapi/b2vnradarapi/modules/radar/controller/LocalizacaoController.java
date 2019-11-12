package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarLocalizacaoResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.service.LocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.List;

@RadaresBaseUrl
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping("localizacao")
    public BaseRadares buscarPorLocalizacao(@PathParam("latitude_longitude") String latitudeLongitude) {
        return localizacaoService.buscarPorLocalizacao(latitudeLongitude);
    }

    @GetMapping("localizacoes/mapa")
    public List<RadarLocalizacaoResponse> buscarLocalizacoesMapa() {
        return localizacaoService.buscarLocalizacoesMapa();
    }

    @GetMapping("localizacoes/mapa/concessao/{lote}")
    public List<RadarLocalizacaoResponse> buscarLocalizacoesMapaComLote(@PathVariable Integer lote) {
        return localizacaoService.buscarLocalizacoesMapaComLote(lote);
    }

    @GetMapping("localizacoes/mapa/concessoes")
    public List<RadarLocalizacaoResponse> buscarPorLotes(@RequestParam("lotes") List<Integer> lotes) {
        return localizacaoService.buscarPorLotes(lotes);
    }

    @GetMapping("localizacoes/mapa/enquadramento/{enquadramento}")
    public List<RadarLocalizacaoResponse> buscarLocalizacoesMapaComEnquadramentos(@PathVariable String enquadramento) {
        return localizacaoService.buscarLocalizacoesMapaComEnquadramentos(enquadramento);
    }
}
