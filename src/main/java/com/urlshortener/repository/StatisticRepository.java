package com.urlshortener.repository;

import com.urlshortener.domain.entity.Statistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface StatisticRepository extends Repository<Statistic, Long> {

    //Statistic findStatisticByAccountId(String accountId);
    //Iterable<Statistic> findAllbyAccountId(String accountId);

    List<Statistic> findByAccountId(String accountId);

}
