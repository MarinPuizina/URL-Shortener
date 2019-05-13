package com.urlshortener.controller;

import com.urlshortener.repository.RegisterRepository;
import com.urlshortener.repository.StatisticJpaRepository;
import com.urlshortener.repository.StatisticRepository;
import com.urlshortener.service.RedirectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

/**
 * RedirectController
 * For redirect request - Call URL with another URL as parameter in browser, increments hits for that link
 * Redirects to specific site
 * e.g.: http://localhost:8080/redirect?shortUrl={URL}
 *
 * @author  Marin Puizina
 */
@Controller
public class RedirectController {

    Logger logger = LoggerFactory.getLogger(RedirectController.class);

    StatisticRepository statisticRepository;
    RegisterRepository registerRepository;
    RedirectService redirectService;
    StatisticJpaRepository statisticJpaRepository;

    // Dependency Injection
    @Autowired
    public RedirectController(StatisticRepository statisticRepository, RegisterRepository registerRepository, RedirectService redirectService, StatisticJpaRepository statisticJpaRepository) {
        this.statisticRepository = statisticRepository;
        this.registerRepository = registerRepository;
        this.redirectService = redirectService;
        this.statisticJpaRepository = statisticJpaRepository;
    }


    @GetMapping("/redirect")
    public RedirectView redirect( HttpServletResponse response, @RequestParam(name = "shortUrl", required = false, defaultValue = "") String shortUrl, RedirectAttributes attributes) {

        // Getting logged in user name
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedAccount = authentication.getName();
        logger.info(authenticatedAccount);

        //redirectService.getDummy(registerRepository, statisticRepository, authentication.getName(), shortUrl);
        String originalUrl = redirectService.getUrlIfExists(registerRepository, shortUrl);
        if (originalUrl == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return new RedirectView("http://localhost:8080/redirect404.html");
        }
        logger.info("Original URL = " + originalUrl);

        // Insering data in database or incrementing hits
        if(!redirectService.isUrlStoredInDatabase(statisticRepository, authenticatedAccount, originalUrl)){
            // Insert new value in the database
            redirectService.inserdNewStatisticInDB(statisticRepository, originalUrl, authenticatedAccount, 1L);

        } else {
            // Incrementing hits
           redirectService.upadeHits(statisticJpaRepository, statisticRepository, authenticatedAccount, originalUrl);
        }


        attributes.addFlashAttribute("flashAttribute", "redirect");
        attributes.addAttribute("attribute", "redirect");


        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);

        return new RedirectView(originalUrl);
    }

}
