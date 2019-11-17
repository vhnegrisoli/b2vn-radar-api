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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RadaresBaseUrl
public class LocalizacaoController {

    @Autowired()
    private LocalizacaoService localizacaoService;

    @GetMapping(value = "localizacao", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public BaseRadares buscarPorLocalizacao(@PathParam("latitude_longitude") String latitudeLongitude) {
        return localizacaoService.buscarPorLocalizacao(latitudeLongitude);
    }

    @GetMapping(value = "localizacoes/mapa", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<RadarLocalizacaoResponse> buscarLocalizacoesMapa() {
        return localizacaoService.buscarLocalizacoesMapa();
    }

    @GetMapping(value = "localizacoes/mapa/concessao/{lote}",
        produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<RadarLocalizacaoResponse> buscarLocalizacoesMapaComLote(@PathVariable Integer lote) {
        return localizacaoService.buscarLocalizacoesMapaComLote(lote);
    }

    @GetMapping(value = "localizacoes/mapa/concessoes", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<RadarLocalizacaoResponse> buscarPorLotes(@RequestParam("lotes") List<Integer> lotes) {
        return localizacaoService.buscarPorLotes(lotes);
    }

    @GetMapping(value = "localizacoes/mapa/enquadramento/{enquadramento}",
        produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<RadarLocalizacaoResponse> buscarLocalizacoesMapaComEnquadramentos(@PathVariable String enquadramento) {
        return localizacaoService.buscarLocalizacoesMapaComEnquadramentos(enquadramento);
    }
}
