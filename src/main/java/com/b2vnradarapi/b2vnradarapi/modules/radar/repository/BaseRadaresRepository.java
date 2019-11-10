package com.b2vnradarapi.b2vnradarapi.modules.radar.repository;

import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BaseRadaresRepository extends JpaRepository<BaseRadares, Integer>,
    BaseRadaresRepositoryCustom {

    Optional<BaseRadares> findByCodigoIgnoreCaseContaining(String codigo);

    Optional<BaseRadares> findByLatitudeLIgnoreCaseContaining(String latitudeL);

    List<BaseRadares> findByLote(Integer lote);

    List<BaseRadares> findByLoteIn(List<Integer> lotes);

    List<BaseRadares> findByEnquadrame(String enquadramento);
}
