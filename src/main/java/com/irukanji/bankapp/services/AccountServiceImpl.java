package com.irukanji.bankapp.services;

import com.irukanji.bankapp.exceptions.NotFoundException;
import com.irukanji.bankapp.model.Account;
import com.irukanji.bankapp.model.Client;
import com.irukanji.bankapp.repositories.AccountRepository;
import com.irukanji.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private ClientRepository clientRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Account createAccount(Long clientId, Account acc) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if(!optionalClient.isPresent()) {
            throw new NotFoundException("Client For ID value: " + clientId.toString() + " Not Found." );
        }

        Client client = optionalClient.get();
        client.getAccounts().add(acc);
        this.clientRepository.saveAndFlush(client);
        return null;
    }

    @Override
    @Transactional
    public Account findAccountById(Long accountId) {
        Optional<Account> optionalAccount = this.accountRepository.findById(accountId);

        if(!optionalAccount.isPresent()){
            throw new NotFoundException("Account For ID value: " + accountId.toString() + " Not Found." );
        }

        return optionalAccount.get();
    }

    @Override
    @Transactional
    public List<Account> findAllAccounts(Long clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if(!optionalClient.isPresent()) {
            throw new NotFoundException("Client For ID value: " + clientId.toString() + " Not Found." );
        }

        Client client = optionalClient.get();
        return client.getAccounts();
    }

    @Override
    @Transactional
    public Account updateAccount(Account account) {
        return this.accountRepository.saveAndFlush(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Long accountId) {

    }
}
