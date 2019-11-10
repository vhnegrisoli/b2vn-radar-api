package com.b2vnradarapi.b2vnradarapi.modules.radar.repository;

import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarContagemResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static com.b2vnradarapi.b2vnradarapi.modules.radar.model.QContagens.contagens;

@Repository
public class ContagensRepositoryImpl implements ContagensRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public RadarContagemResponse findFluxoVeiculosByCodigo(Integer codigo) {
        return new JPAQuery<Void>(entityManager)
            .select(Projections.constructor(
                RadarContagemResponse.class,
                contagens.contagem.sum(),
                contagens.contagem.count()
            ))
            .from(contagens)
            .where(contagens.localidade.eq(codigo))
            .fetchOne();
    }

    @Override
    public RadarContagemResponse findFluxoVeiculosByCodigoAndDataHora(Integer codigo,
                                                           LocalDateTime dataHoraInicial,
                                                           LocalDateTime dataHoraFinal) {
        return new JPAQuery<Void>(entityManager)
            .select(Projections.constructor(
                RadarContagemResponse.class,
                contagens.contagem.sum(),
                contagens.contagem.count()
            ))
            .from(contagens)
            .where(contagens.localidade.eq(codigo)
            .and(contagens.dataHora.between(dataHoraFinal, dataHoraInicial)))
            .fetchOne();
    }
}
