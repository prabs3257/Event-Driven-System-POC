package com.prabs.account.query.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface AccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findByAccountHolder(String accountHolder);
}
