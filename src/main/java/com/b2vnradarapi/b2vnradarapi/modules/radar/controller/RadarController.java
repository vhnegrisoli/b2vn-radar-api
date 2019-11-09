package com.b2vnradarapi.b2vnradarapi.modules.radar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/radares")
public class RadarController {

    @GetMapping("teste")
    public String teste() {
        return "O Rafael é um ruivinho da bundinha lisinha";
    }
}
