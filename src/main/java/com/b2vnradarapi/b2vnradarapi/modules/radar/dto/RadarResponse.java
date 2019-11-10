package com.b2vnradarapi.b2vnradarapi.modules.radar.dto;

import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.util.ObjectUtils.isEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RadarResponse {

    private static final String ESPACO_EM_BRANCO = " ";

    private Integer id;
    private Double latitude;
    private Double longitude;
    private String latitudeLongitude;
    private String velocidade;
    private Integer lote;

    public static RadarResponse of(BaseRadares baseRadares) {
        var response = new RadarResponse();
        response.setId(baseRadares.getId());
        response.setLatitude(getLatitude(baseRadares.getLatitudeL()));
        response.setLongitude(getLongitude(baseRadares.getLatitudeL()));
        response.setVelocidade(baseRadares.getVelocidade());
        response.setLatitudeLongitude(baseRadares.getLatitudeL());
        response.setLote(baseRadares.getLote());
        return response;
    }

    private static Double getLatitude(String dados) {
        if (!isEmpty(dados) && dados.contains(ESPACO_EM_BRANCO)) {
            dados = dados.replace("(", "").replace(")", "");
            var dadosArr = dados.split(ESPACO_EM_BRANCO);
            return isEmpty(dadosArr[0]) ? 0.0 : Double.parseDouble(dadosArr[0]);
        }
        return 0.0;
    }

    private static Double getLongitude(String dados) {
        if (!isEmpty(dados) && dados.contains(ESPACO_EM_BRANCO)) {
            dados = dados.replace("(", "").replace(")", "");
            var dadosArr = dados.split(ESPACO_EM_BRANCO);
            return isEmpty(dadosArr[1]) ? 0.0 : Double.parseDouble(dadosArr[1]);
        }
        return 0.0;
    }
}