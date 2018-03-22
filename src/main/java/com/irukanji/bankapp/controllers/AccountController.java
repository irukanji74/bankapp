package com.irukanji.bankapp.controllers;

import com.irukanji.bankapp.model.Account;
import com.irukanji.bankapp.model.Client;
import com.irukanji.bankapp.services.AccountService;
import com.irukanji.bankapp.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Controller
public class AccountController {

    private ClientService clientService;
    private AccountService accountService;

    @Autowired
    public AccountController(ClientService clientService, AccountService accountService) {
        this.clientService = clientService;
        this.accountService = accountService;
    }

    @RequestMapping("client/{id}/showAccounts")
    public String showById(@PathVariable String id, Model model, HttpSession session) {

        Client client = clientService.findById(new Long(id));
        model.addAttribute("accounts", client.getAccounts());
        model.addAttribute("account", new Account());
        model.addAttribute("client", client);

        session.setAttribute("clientId", Long.parseLong(id));

        return "accounts";
    }

    @PostMapping("new_account")
    public String addNewAccount(@Valid @ModelAttribute("account") Account account
            , HttpSession session
            , BindingResult bindingResult
            , Model model){

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            Long id = (Long)session.getAttribute("clientId");
            log.debug(id.toString());
            return "client/" + id + "/showAccounts";//TODO
        }

        Long id = (Long)session.getAttribute("clientId");
        Client client = this.clientService.findById(id);

        account.setClient(client);
        client.getAccounts().add(account);
        this.clientService.editClientInfo(client);

        model.addAttribute("accounts", client.getAccounts());
        model.addAttribute("account", new Account());
        model.addAttribute("client", client);

        return "redirect:/client/"+ client.getId() + "/showAccounts";
    }
}

