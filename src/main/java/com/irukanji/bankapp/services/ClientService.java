package com.irukanji.bankapp.services;

import com.irukanji.bankapp.model.Client;

import java.util.List;

public interface ClientService {

    Client addClient(Client client);

    List<Client> getAllClients();

    void deleteClient(Long clientId);

    Client editClientInfo(Client client);

    Client findByName(String name);

    Client findById(Long id);
}
