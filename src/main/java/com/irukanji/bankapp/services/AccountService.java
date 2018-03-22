package com.irukanji.bankapp.services;

import com.irukanji.bankapp.model.Account;

import java.util.List;
import java.util.Set;

public interface AccountService {

    Account createAccount(Long clientId, Account account);

    Account findAccountById(Long accountId);

    List<Account> findAllAccounts(Long clientId);

    Account updateAccount(Account account);

    void deleteAccount(Long accountId);
}
