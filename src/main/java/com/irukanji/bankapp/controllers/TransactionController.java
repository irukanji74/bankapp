package com.irukanji.bankapp.controllers;

import com.irukanji.bankapp.model.Account;
import com.irukanji.bankapp.model.Client;
import com.irukanji.bankapp.model.Transaction;
import com.irukanji.bankapp.services.AccountService;
import com.irukanji.bankapp.services.ClientService;
import com.irukanji.bankapp.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class TransactionController {

    TransactionService transactionService;
    AccountService accountService;
    ClientService clientService;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService, ClientService clientService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.clientService = clientService;
    }

    @RequestMapping("accounts/{id}/showTxList")
    public String showTxListForAccountId(@PathVariable String id, Model model, HttpSession session) {
        List<Transaction> txListOfAccount = this.transactionService.findTxListForAccount(id);
        model.addAttribute("txList", txListOfAccount);

        return "transactions_list";
    }

    @RequestMapping("transact")
    public String getTransactionPage(Model model){
        model.addAttribute("transaction", new Transaction());

        return "transaction";
    }

    @GetMapping("transList")
    public String showTxList(Model model){

        List<Transaction> txList = this.transactionService.findAll();
        model.addAttribute("txList", txList);

        return "transactions_list";
    }

    @PostMapping("transact")
    public String sendMoney(@Valid @ModelAttribute("transaction")Transaction tx, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "transaction";
        }

        this.transactionService.sendMoney(tx);

        return "redirect:/transact";
    }

    @PostMapping("sort")
    public String filterSearch(@RequestParam(value="id",required=false) String clientId
            ,@RequestParam(value="dateFrom",required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom
            ,@RequestParam(value="dateTo",required=false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo
            , Model model){

        log.debug("date of creation " + dateFrom + " dateTo " + dateTo + " clientId " + clientId);
        List<Transaction> filteredTxList;

        if(!clientId.isEmpty() && dateFrom == null && dateTo == null){
            List<Account> accountList = this.accountService.findAllAccounts(Long.parseLong(clientId));

            List<Long> accountIds = accountList.stream().map(acc -> acc.getId()).collect(Collectors.toList());

            filteredTxList = this.transactionService.findTxByClient(accountIds);

            model.addAttribute("txList", filteredTxList);

            return "transactions_list";
        }

        if(clientId.isEmpty() && dateFrom != null && dateTo != null){
            filteredTxList = this.transactionService.findTxListByPeriod(dateFrom, dateTo);
            model.addAttribute("txList", filteredTxList);

            return "transactions_list";

        }

        if(!clientId.isEmpty() && dateFrom != null && dateTo != null){
            //TODO
          /*  List<Account> accountList = this.accountService.findAllAccounts(Long.parseLong(clientId));
            List<Long> accountIds = accountList.stream().map(acc -> acc.getId()).collect(Collectors.toList());

            List<Transaction> tempList = this.transactionService.findTxByClient(accountIds);


            filteredTxList = this.transactionService.findTxByDates(dateFrom, dateTo, tempList);
            model.addAttribute("txList", filteredTxList);

            return "transactions_list";*/

        }

        return "transactions_list";

    }


}
