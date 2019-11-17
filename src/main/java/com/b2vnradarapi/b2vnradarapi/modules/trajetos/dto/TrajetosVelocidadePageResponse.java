package com.b2vnradarapi.b2vnradarapi.modules.trajetos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class TrajetosVelocidadePageResponse {

    private Long totalElements;
    private Page<TrajetosVelocidadesMediasResponse> trajetosVelocidadesMediasResponse;

}
