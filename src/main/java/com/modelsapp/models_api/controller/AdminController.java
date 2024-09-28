package com.modelsapp.models_api.controller;

import com.modelsapp.models_api.account.Account;
import com.modelsapp.models_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        try {
            Account createdAccount = accountService.createNewAccount(account);
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // E-mail ou nome de usuário já existe
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateAccount(
            @PathVariable UUID id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam int age,
            @RequestParam String email) {
        try {
            Account updatedAccount = accountService.updateAccount(id, firstName, lastName, age, email);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable UUID id) {
        Account account = new Account(); // Você pode precisar de um método para encontrar a conta pelo ID antes de criar o objeto
        account.setId(id);
        try {
            Account deletedAccount = accountService.deleteAccount(account);
            return ResponseEntity.ok(deletedAccount);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Account> findAccountById(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email) {
        try {
            Account account = accountService.findAccountByUserOrEmail(username, email);
            return ResponseEntity.ok(account);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Parâmetros inválidos
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Conta não encontrada
        }
    }
}
