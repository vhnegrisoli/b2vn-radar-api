package com.b2vnradarapi.b2vnradarapi.modules.radar.repository;

import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.RadarContagemResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.TiposPorRadarResponse;
import com.b2vnradarapi.b2vnradarapi.modules.radar.dto.TiposRadarTotais;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<TiposPorRadarResponse> findTiposPorRadar(Integer localidade) {
        return new JPAQuery<Void>(entityManager)
            .select(Projections.constructor(TiposPorRadarResponse.class,
                contagens.localidade,
                contagens.tipo,
                contagens.tipo.count()))
            .from(contagens)
            .where(contagens.localidade.like("%" + localidade + "%"))
            .groupBy(contagens.localidade, contagens.tipo)
            .fetch();
    }

    @Override
    public List<TiposRadarTotais> findTipos() {
        return new JPAQuery<Void>(entityManager)
            .select(Projections.constructor(TiposRadarTotais.class,
                contagens.tipo,
                contagens.tipo.count()))
            .from(contagens)
            .groupBy(contagens.tipo)
            .fetch();
    }

    @Override
    public List<TiposPorRadarResponse> findTiposPorRadares() {
        return new JPAQuery<Void>(entityManager)
            .select(Projections.constructor(TiposPorRadarResponse.class,
                contagens.localidade,
                contagens.tipo,
                contagens.tipo.count()))
            .from(contagens)
            .groupBy(contagens.localidade, contagens.tipo)
            .orderBy(contagens.localidade.asc())
            .fetch();
    }
}
