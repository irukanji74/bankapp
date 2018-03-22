package com.irukanji.bankapp.controllers;

import com.irukanji.bankapp.model.Client;
import com.irukanji.bankapp.model.Transaction;
import com.irukanji.bankapp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPageController {

    private ClientService clientService;

    @Autowired
    public IndexPageController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("client", new Client());
        model.addAttribute("transaction", new Transaction());

        return "index";
    }
}
