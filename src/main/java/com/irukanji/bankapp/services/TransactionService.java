package com.irukanji.bankapp.services;

import com.irukanji.bankapp.model.Transaction;
import com.irukanji.bankapp.repositories.TransactionRepository;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public interface TransactionService {
    Boolean sendMoney(@Valid Transaction tx);
    List<Transaction> findAll();

    List<Transaction> findTxByClient(List<Long> accountIds);

    List<Transaction> findTxByDates(Date dateFrom, Date dateTo, List<Transaction> list);

    List<Transaction> findTxListByPeriod(Date dateFrom, Date dateTo);
}
