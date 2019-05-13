package com.urlshortener.service;

import com.urlshortener.domain.entity.Register;
import com.urlshortener.domain.entity.Statistic;
import com.urlshortener.repository.RegisterRepository;
import com.urlshortener.repository.StatisticJpaRepository;
import com.urlshortener.repository.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RedirectService Service
 * Business Logic used in RedirectController
 *
 * @author  Marin Puizina
 */
@Service
public class RedirectService {

    Logger logger = LoggerFactory.getLogger(RedirectService.class);

    /**
     *
     * @param registerRepository used to call methods to query the database
     * @param shortUrl used in query to get original URL
     * @return original URL
     */
    public String getUrlFromShortUrl(RegisterRepository registerRepository, String shortUrl){

        logger.info("Using Short URL to find URL in database = " + shortUrl);

        Register registerValues = registerRepository.findByShortUrl(shortUrl);

        if(registerValues == null){
            logger.info("Nothing found in database.");
            return null;
        }

        logger.info("Found URL in database = " + registerValues.getUrl());

        return registerValues.getUrl();

    }

    /**
     *
     * @param statisticRepository used to call methods to query the database
     * @param accountId used in query to get Statistic
     * @param url user in query to get Statistic
     * @return
     */
    public boolean isUrlStoredInDatabase(StatisticRepository statisticRepository, String accountId, String url){

        List<Statistic> statsValues = statisticRepository.findByAccountIdAndUrl(accountId, url);
        logger.info("statsValues = " + statsValues);

        if(statsValues == null || statsValues.isEmpty()) {
            return false;
        }

        return true;

    }

    /**
     * Method for insering values in Statistic table in database
     *
     *
     * @param statisticRepository
     * @param originalUrl value used to store in database
     * @param accountId value used to store in database
     * @param hits value used to store in database
     * @return
     */
    public boolean inserdNewStatisticInDB(StatisticRepository statisticRepository, String originalUrl, String accountId, Long hits){

        statisticRepository.save(new Statistic(originalUrl, accountId, hits));

        logger.info("------------------------------");
        logger.info("URL = " + originalUrl);
        logger.info("accountId = " + originalUrl);
        logger.info("hits = " + hits);
        logger.info("Have been stored in database.");
        logger.info("------------------------------");


        return true;

    }

    /**
     *
     * @param registerRepository used to query the databse
     * @param shortUrl used to find original URL from database
     * @return null if query failed or nothing was found OR URL value
     */
    public String getUrlIfExists(RegisterRepository registerRepository, String shortUrl) {

        String url = getUrlFromShortUrl(registerRepository, shortUrl);
        if(url == null){
            return null;
        }

        return  url;

    }

    /**
     *
     * @param statisticJpaRepository used to do upadate query
     * @param authenticatedAccount name of authenticated account
     * @param originalUrl original url
     */
    public void upadeHits(StatisticJpaRepository statisticJpaRepository, StatisticRepository statisticRepository, String authenticatedAccount, String originalUrl) {

        List<Statistic> statsValues = statisticRepository.findByAccountIdAndUrl(authenticatedAccount, originalUrl);
        logger.info("statsValues = " + statsValues);

        for (Statistic stats : statsValues) {
            statisticJpaRepository.updateHits(authenticatedAccount, stats.getHits()+1, originalUrl);
        }
        logger.info("Incrementing Hits - DONE");

    }

}
