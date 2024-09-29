package com.lautadev.MyApplications.service;

import com.lautadev.MyApplications.model.Account;
import com.lautadev.MyApplications.model.GoogleUserInfo;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    public Account saveAccount(Account account);
    public List<Account> getAccounts();
    public Optional<Account> findAccount(Long id);
    public void deleteAccount(Long id);
    public Account editAccount(Long id, Account account);
    public Account saveUserOAuth(GoogleUserInfo googleUserInfo);
    public String encriptPassword(String password);
}
