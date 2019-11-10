package com.b2vnradarapi.b2vnradarapi.modules.radar.repository;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.b2vnradarapi.b2vnradarapi.modules.radar.model.QBaseRadares.baseRadares;

@Repository
public class BaseRadaresRepositoryImpl implements BaseRadaresRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Integer> findLoteDistict() {
        return new JPAQuery<Void>(entityManager)
            .select(baseRadares.lote).distinct()
            .from(baseRadares)
            .orderBy(baseRadares.lote.asc())
            .fetch();
    }

    @Override
    public List<String> findEnquadramentoDistict() {
        return new JPAQuery<Void>(entityManager)
            .select(baseRadares.enquadrame).distinct()
            .from(baseRadares)
            .where(baseRadares.enquadrame.isNotNull())
            .orderBy(baseRadares.enquadrame.asc())
            .fetch();
    }
}
