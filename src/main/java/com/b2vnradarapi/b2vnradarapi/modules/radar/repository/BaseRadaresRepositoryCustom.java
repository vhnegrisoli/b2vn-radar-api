package com.b2vnradarapi.b2vnradarapi.modules.radar.repository;

import java.util.List;

public interface BaseRadaresRepositoryCustom {

    List<Integer> findLoteDistict();

    List<String> findEnquadramentoDistict();
}
