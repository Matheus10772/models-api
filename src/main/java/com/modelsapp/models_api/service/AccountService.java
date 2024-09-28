package com.modelsapp.models_api.service;

import com.modelsapp.models_api.account.Account;
import com.modelsapp.models_api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;



    public Account createNewAccount(Account account) throws AccountNotFoundException {
        Optional<Account> accountExistsEmail = accountRepository.findAccountByEmail(account.getEmail());
        Optional<Account> accountExistsUsername = accountRepository.findAccountByUser(account.getUsername());

        if(accountExistsEmail.isPresent()) {
            throw new AccountNotFoundException("O e-mail informado já existe.");
        } else if(accountExistsUsername.isPresent()) {
            throw new AccountNotFoundException("O nome de usuário informado já existe.");
        } else{
            return accountRepository.save(account);
        }
    }

    public Account resetPassword(Account account, String newPassword) throws AccountNotFoundException {
        Optional<Account> userAccount = accountRepository.findAccountById(account.getId());

        if(userAccount.isPresent()) {
            Account accountToUpdate = userAccount.get();
            accountToUpdate.setSenha(newPassword);
            return accountRepository.save(accountToUpdate);
        } else {
            throw new AccountNotFoundException("A conta informada não foi encontrada.");
        }
    }

    public Account updateAccount(UUID accountID, String firstName, String lastName, int age, String email) throws AccountNotFoundException {
        Optional<Account> accountToUpdate = accountRepository.findAccountById(accountID);
        if(accountToUpdate.isPresent()) {
            Account account = accountToUpdate.get();
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setAge(age);
            account.setEmail(email);
            return accountRepository.save(account);
        } else {
            throw new AccountNotFoundException("A conta informada para atualizar, não foi encontrada.");
        }
    }

    public Account deleteAccount(Account account) throws AccountNotFoundException {
        Optional<Account> accountToDelete = accountRepository.findAccountById(account.getId());
        if(accountToDelete.isPresent()) {
            accountRepository.delete(accountToDelete.get());
            return accountToDelete.get();
        } else {
            throw new AccountNotFoundException("A conta informada não foi encontrada.");
        }
    }

    public Account findAccountByUserOrEmail(String userName, String email) throws AccountNotFoundException {
        if(userName.isEmpty() && email.isEmpty()) {
            throw new IllegalArgumentException("É necessário fornecer ao menos um parâmetro para a busca");
        } else {
            Optional<Account> accountToFind = accountRepository.findAccountByUserOrEmail(userName, email);

            if(accountToFind.isPresent()) {
                return accountToFind.get();
            } else {
                throw new AccountNotFoundException("Conta não encontrada. Nome de usuário ou e-mail incorreto.");
            }
        }

    }




}
