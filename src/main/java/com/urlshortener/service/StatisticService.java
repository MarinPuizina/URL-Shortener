package com.urlshortener.service;

import com.urlshortener.domain.entity.Statistic;
import com.urlshortener.repository.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * StatisticService Service
 * Business Logic used in StatisticController
 *
 * @author  Marin Puizina
 */
@Service
public class StatisticService {

    Logger logger = LoggerFactory.getLogger(StatisticService.class);


    public Map<String, Long> getAccessedUrlAndHitsCounter(StatisticRepository statisticRepository, String accountId, Map<String, Long> statisticValues) {

        //Statistic stats = statisticRepository.findStatisticByAccountId(accountId);
        //Iterable<Statistic> statsList = statisticRepository.findAllbyAccountId(accountId);

        List<Statistic> statsValues = statisticRepository.findByAccountId(accountId);
        logger.info("lista = " + statsValues);

        if (statsValues == null) {
            return null;
        }

        for (Statistic stats : statsValues) {

            statisticValues.put(stats.getUrl(), stats.getHits());

            //logger.info("URL = " + stats.getUrl());
            //logger.info("HITS = " + stats.getHits());

        }

        return statisticValues;

    }

}
