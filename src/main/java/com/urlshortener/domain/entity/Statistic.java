package com.urlshortener.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String accountId;
    private Long hits;


    // No arg constructor needed by JPA
    public Statistic() {
    }

    public Statistic(String url, String accountId, Long hits) {
        this.url = url;
        this.accountId = accountId;
        this.hits = hits;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getHits() {
        return hits;
    }

    public void setHits(Long hits) {
        this.hits = hits;
    }


    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", accountId='" + accountId + '\'' +
                ", hits=" + hits +
                '}';
    }

}
