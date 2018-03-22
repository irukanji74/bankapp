package com.irukanji.bankapp.controllers;

import com.irukanji.bankapp.model.Client;
import com.irukanji.bankapp.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Controller
public class ClientController {

    private static final String CLIENT_ADD_PAGE= "index";

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("client")
    public String addNewClient(@Valid @ModelAttribute("client") Client client
            , BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return CLIENT_ADD_PAGE;
        }

        this.clientService.addClient(client);

        return "redirect:/index";
    }



}
