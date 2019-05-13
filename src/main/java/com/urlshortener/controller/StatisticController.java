package com.urlshortener.controller;

import com.urlshortener.repository.StatisticRepository;
import com.urlshortener.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StatisticController {

    Logger logger = LoggerFactory.getLogger(StatisticController.class);

    StatisticService statisticService;
    StatisticRepository statisticRepository;

    @Autowired
    public StatisticController(StatisticService statisticService, StatisticRepository statisticRepository) {
        this.statisticService = statisticService;
        this.statisticRepository = statisticRepository;
    }


    @RequestMapping(value = "/statistic/{accountId}", method = RequestMethod.GET)
    public Map<String, Long> statistic(@PathVariable(value = "accountId") String accountId) {

        logger.info("accountId = " + accountId);

        Map<String, Long> statisticValues = new HashMap<>();

        statisticService.getAccessedUrlAndHitsCounter(statisticRepository, accountId, statisticValues);

        return statisticValues;

    }

}
