package com.urlshortener.repository;

import com.urlshortener.domain.entity.Statistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface StatisticRepository extends Repository<Statistic, Long>, CrudRepository<Statistic, Long>{

    List<Statistic> findByAccountId(String accountId);
    List<Statistic> findByAccountIdAndUrl(String accountId, String url);
/*
    @Modifying
    @Query("UPDATE statistic s SET s.hits = :hits WHERE s.account_id = :accountId AND s.url = :url")
    void updateHits(@Param("accountId") String accountId, @Param("hits") Long hits, @Param("url") String url);*/

}
