package com.urlshortener.repository;

import com.urlshortener.domain.entity.Statistic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class StatisticInsertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(Statistic statistic) {
        entityManager.createNativeQuery("INSERT INTO statistic (account_Id, hits, url) VALUES (?,?,?)")
                .setParameter(1, statistic.getAccountId())
                .setParameter(2, statistic.getHits())
                .setParameter(3, statistic.getUrl())
                .executeUpdate();
    }

    @Transactional
    public void insertWithEntityManager(Statistic statistic) {
        this.entityManager.persist(statistic);
    }

}
