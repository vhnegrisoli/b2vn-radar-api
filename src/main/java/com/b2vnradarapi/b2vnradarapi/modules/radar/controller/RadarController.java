package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import com.b2vnradarapi.b2vnradarapi.config.exception.ValidacaoException;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.Contagens;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.Trajetos;
import com.b2vnradarapi.b2vnradarapi.modules.radar.model.Viagens;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.ContagensRepository;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.TrajetosRepository;
import com.b2vnradarapi.b2vnradarapi.modules.radar.repository.ViagensRepository;
import com.b2vnradarapi.b2vnradarapi.modules.radar.service.RadarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/radares")
public class RadarController {

    @Autowired
    private RadarService radarService;
    @Autowired
    private ContagensRepository contagensRepository;
    @Autowired
    private TrajetosRepository trajetosRepository;
    @Autowired
    private ViagensRepository viagensRepository;

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

    @GetMapping("concessoes-selecao")
    public List<RadarResponse> buscarPorLotes(@RequestParam("lotes") List<Integer> lotes) {
        return radarService.buscarPorLotes(lotes);
    }

    @GetMapping("contagens/{id}")
    public Contagens buscarUmaContagem(@PathVariable Integer id) {
        return contagensRepository.findById(id).orElseThrow(() -> new ValidacaoException("Não encontrado"));
    }

    @GetMapping("trajetos/{id}")
    public Trajetos buscarUmTrajeto(@PathVariable Integer id) {
        return trajetosRepository.findById(id).orElseThrow(() -> new ValidacaoException("Não encontrado"));
    }

    @GetMapping("viagens/{id}")
    public Viagens buscarUmaViagem(@PathVariable Integer id) {
        return viagensRepository.findById(id).orElseThrow(() -> new ValidacaoException("Não encontrado"));
    }
}
