package com.irukanji.bankapp.services;

import com.irukanji.bankapp.exceptions.NotFoundException;
import com.irukanji.bankapp.model.Account;
import com.irukanji.bankapp.model.Client;
import com.irukanji.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Client addClient(Client client) {
        Client savedClient = this.clientRepository.saveAndFlush(client);
        return savedClient;
    }

    @Override
    @Transactional
    public List<Client> getAllClients() {
        List<Client> clientList = new ArrayList<>();
        this.clientRepository.findAll().iterator().forEachRemaining(clientList::add);
        return clientList;
    }

    @Override
    @Transactional
    public void deleteClient(Long clientId) {
           this.clientRepository.deleteById(clientId);
    }

    @Override
    @Transactional
    public Client editClientInfo(Client client) {
        Client updatedClient = this.clientRepository.saveAndFlush(client);
        return updatedClient;
    }

    @Override
    @Transactional
    public Client findByName(String name) {
        Client client = this.clientRepository.findByName(name);
        return client;
    }

    @Override
    public Client findById(Long clientId) {
        Optional<Client> optionalAccount = this.clientRepository.findById(clientId);

        if(!optionalAccount.isPresent()){
            throw new NotFoundException("Client For ID value: " + clientId.toString() + " Not Found." );
        }

        return optionalAccount.get();
    }


}
