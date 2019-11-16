package com.b2vnradarapi.b2vnradarapi.modules.acidentes.service;

import com.b2vnradarapi.b2vnradarapi.config.exception.ValidacaoException;
import com.b2vnradarapi.b2vnradarapi.modules.acidentes.dto.TipoVeiculoResponse;
import com.b2vnradarapi.b2vnradarapi.modules.acidentes.client.AcidentesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoVeiculoService {

    private static final ValidacaoException TIPO_VEICULO_NAO_ENCONTRADO =
        new ValidacaoException("Tipo de veículo não encontrado.");

    @Autowired
    private AcidentesClient acidentesClient;

    public TipoVeiculoResponse buscarPorVeiculos(String veiculo) {
        var tipoVeiculo = Optional.ofNullable(acidentesClient.buscarPorVeiculo(veiculo));
        return tipoVeiculo.orElseThrow(() -> TIPO_VEICULO_NAO_ENCONTRADO);
    }

    public TipoVeiculoResponse buscarPorTipoDeVeiculos(Integer tipo) {
        var tipoVeiculo = Optional.ofNullable(acidentesClient.buscarPorTipo(tipo));
        return tipoVeiculo.orElseThrow(() -> TIPO_VEICULO_NAO_ENCONTRADO);
    }
}
