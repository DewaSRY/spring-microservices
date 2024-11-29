package com.sdewa.account.repository;

import com.sdewa.account.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerId(long customerId);

    @Transactional
    @Modifying
    void  deleteByCustomerId(Long customerId);
}
