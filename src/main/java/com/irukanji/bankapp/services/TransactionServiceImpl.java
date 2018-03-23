package com.irukanji.bankapp.services;

import com.irukanji.bankapp.model.Account;
import com.irukanji.bankapp.model.Transaction;
import com.irukanji.bankapp.repositories.AccountRepository;
import com.irukanji.bankapp.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public Boolean sendMoney(@Valid Transaction tx) {
        Optional<Account> optionalAccountTo = this.accountRepository.findById(tx.getAccountIdTo());
        Optional<Account> optionalAccountFrom = this.accountRepository.findById(tx.getAccountIdFrom());

        if(!optionalAccountFrom.isPresent() || (optionalAccountFrom.get().getAccountBalance()).compareTo(tx.getTxAmount()) == -1
                                            || !optionalAccountTo.isPresent()){
            return false;
        }

        Account from = optionalAccountFrom.get();
        BigDecimal totalBalance = from.getAccountBalance();
        from.setAccountBalance(totalBalance.subtract(tx.getTxAmount()));
        this.accountRepository.saveAndFlush(from);

        Account to = optionalAccountTo.get();
        totalBalance = to.getAccountBalance();
        to.setAccountBalance(totalBalance.add(tx.getTxAmount()));
        this.accountRepository.saveAndFlush(to);

        tx.setTxTime(new Date());
        this.transactionRepository.save(tx);

        return true;
    }

    @Override
    @Transactional
    public List<Transaction> findAll() {
        return this.transactionRepository.findAll();
    }

    @Override
    @Transactional
    public List<Transaction> findTxByClient(List<Long> accountIds) {

        List<Transaction> txList = new ArrayList<>();

        for (Long id:accountIds) {
            List<Transaction> tx =  this.transactionRepository.findTxByAccountId(id);
            txList.addAll(tx);
        }
        return txList;
    }

    @Override
    @Transactional
    public List<Transaction> findTxByDates(Date dateFrom, Date dateTo,  List<Transaction> txList) {


        return null;
    }

    @Override
    @Transactional
    public List<Transaction> findTxListByPeriod(Date dateFrom, Date dateTo) {
        return this.transactionRepository.findTxListByPeriod(dateFrom, dateTo);
    }

    @Override
    public List<Transaction> findTxListForAccount(String id) {
        if(id != null) {
            return this.transactionRepository.findTxByAccountId(Long.parseLong(id));
        }

        return new ArrayList<Transaction>();
    }
}
