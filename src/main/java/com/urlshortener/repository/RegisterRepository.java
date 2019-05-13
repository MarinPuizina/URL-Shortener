package com.urlshortener.repository;

import com.urlshortener.domain.entity.Register;
import org.springframework.data.repository.CrudRepository;

public interface RegisterRepository extends CrudRepository<Register, Long> {

    Register findByShortUrl(String shorUrl);

}