package com.b2vnradarapi.b2vnradarapi.modules.relatorios.controller;

import com.b2vnradarapi.b2vnradarapi.modules.relatorios.service.RelatorioCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/relatorios/csv")
public class RelatorioCsvController {

    @Autowired
    private RelatorioCsvService relatorioCsvService;

    @GetMapping(value = "radares", produces = "text/csv")
    public @ResponseBody String getCsv(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=extracao_base_radares.csv");
        return relatorioCsvService.gerarRelatorioCsvRadares();
    }

    @GetMapping(value = "contagens", produces = "text/csv")
    public @ResponseBody String getCsv(@PathParam("dataInicial") String dataInicial,
                                       @PathParam("dataFinal") String dataFinal,
                                       HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=extracao_base_contagens.csv");
        return relatorioCsvService.gerarRelatorioCsvContagens(dataInicial, dataFinal);
    }
}
