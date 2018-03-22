package com.irukanji.bankapp;

import com.irukanji.bankapp.model.Client;
import com.irukanji.bankapp.repositories.AccountRepository;
import com.irukanji.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    @Autowired
    public Bootstrap(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Inserting data in tables");
        fillTables();
    }

    private void fillTables() {

        Client client = new Client("Thomas","Elm str", "45", 55);
       // Account account =  accountRepository.findById(4L).orElse(new Account(client, new BigDecimal("35422")));
        //client.getAccounts().add(account);

        //Account account1 =  accountRepository.findById(5L).orElse(new Account(client, new BigDecimal("898700")));
        Client client1 = new Client("Jack", "Horror str.", "89", 23);
        //client1.getAccounts().add(account1);

     /*   accountRepository.saveAndFlush(account);
        accountRepository.saveAndFlush(account1);
*/
        clientRepository.saveAndFlush(client);
        clientRepository.saveAndFlush(client1);
    }
}
