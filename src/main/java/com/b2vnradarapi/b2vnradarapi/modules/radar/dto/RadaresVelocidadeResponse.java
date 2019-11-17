package com.b2vnradarapi.b2vnradarapi.modules.radar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@NoArgsConstructor
@XmlRootElement
@AllArgsConstructor
public class RadaresVelocidadeResponse {

    private String velocidade;
    private Long totalRadares;

}
