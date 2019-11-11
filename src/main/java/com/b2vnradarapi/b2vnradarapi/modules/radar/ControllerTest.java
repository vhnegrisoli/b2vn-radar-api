package com.b2vnradarapi.b2vnradarapi.modules.radar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class ControllerTest {

    @GetMapping
    public String teste() {
        return "Conexão funcionando!";
    }
}
