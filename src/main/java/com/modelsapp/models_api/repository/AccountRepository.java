package com.modelsapp.models_api.repository;

import com.modelsapp.models_api.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    public Optional<Account> findAccountByEmail(String email);

    public Optional<Account> findAccountByUser(String username);

    public Optional<Account> findAccountById(UUID id);

    public Optional<Account> findAccountByUserOrEmail (String userName, String email);
}
