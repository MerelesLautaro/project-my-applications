package com.lautadev.MyApplications.repository;

import com.lautadev.MyApplications.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findUserEntityByUsername(String username);
}
