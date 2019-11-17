package com.b2vnradarapi.b2vnradarapi.modules.trajetos.dto;

import com.b2vnradarapi.b2vnradarapi.modules.trajetos.model.Trajetos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class TrajetoDistanciaResponse {

    private Trajetos trajeto;
    private BigDecimal distancia;

}
