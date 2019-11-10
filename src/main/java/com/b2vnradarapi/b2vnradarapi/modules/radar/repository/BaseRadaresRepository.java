package com.b2vnradarapi.b2vnradarapi.modules.radar.repository;

import com.b2vnradarapi.b2vnradarapi.modules.radar.model.BaseRadares;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseRadaresRepository extends JpaRepository<BaseRadares, Integer> {

    Optional<BaseRadares> findByCodigoIgnoreCaseContaining(String codigo);
}
