package com.lautadev.MyApplications.controller;

import com.lautadev.MyApplications.model.Account;
import com.lautadev.MyApplications.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@PreAuthorize("permitAll()")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @PostMapping("/save")
    public ResponseEntity<String> saveAccount(@RequestBody Account account){
        accountService.saveAccount(account);
        return ResponseEntity.ok("Account saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Account>> getAccounts(){
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Account> findAccount(@PathVariable Long id){
        Optional<Account> account = accountService.findAccount(id);
        return account.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Account> editAccount(@PathVariable Long id, @RequestBody Account account){
        accountService.editAccount(id,account);
        Optional<Account> accountEdit = accountService.findAccount(account.getId());
        return accountEdit.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
