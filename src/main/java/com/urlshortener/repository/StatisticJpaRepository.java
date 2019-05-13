package com.urlshortener.repository;

import com.urlshortener.domain.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StatisticJpaRepository extends JpaRepository<Statistic, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE statistic s SET s.hits = :hits WHERE s.account_id = :account_Id AND s.url = :url", nativeQuery = true)
    void updateHits(@Param("account_Id") String account_Id, @Param("hits") Long hits, @Param("url") String url);

}
