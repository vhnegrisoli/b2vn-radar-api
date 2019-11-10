package com.b2vnradarapi.b2vnradarapi.modules.radar.repository;

import com.b2vnradarapi.b2vnradarapi.modules.radar.model.Contagens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContagensRepository extends JpaRepository<Contagens, Integer>,
    ContagensRepositoryCustom {
}
