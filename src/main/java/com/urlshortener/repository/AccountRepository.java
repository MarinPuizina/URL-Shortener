package com.urlshortener.repository;

import com.urlshortener.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findAccountByAccountId(String accountId);

}
