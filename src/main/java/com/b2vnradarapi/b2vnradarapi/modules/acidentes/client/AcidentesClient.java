package com.b2vnradarapi.b2vnradarapi.modules.acidentes.client;

import com.b2vnradarapi.b2vnradarapi.modules.acidentes.dto.TipoVeiculoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    contextId = "acidentesClient",
    name = "tipoVeiculosClient",
    url = "${app-config.services.b2vn-acidentes-api.url}")
public interface AcidentesClient {

    @GetMapping("/api/tipos-veiculos/veiculo/{veiculo}")
    TipoVeiculoResponse buscarPorVeiculo(@PathVariable String veiculo);

    @GetMapping("/api/tipos-veiculos/tipo/{tipo}")
    TipoVeiculoResponse buscarPorTipo(@PathVariable Integer tipo);
}
