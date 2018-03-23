package com.irukanji.bankapp.repositories;

import com.irukanji.bankapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select tx from Transaction tx where tx.accountIdFrom = :accountId or tx.accountIdTo = :accountId")
    List<Transaction> findTxByAccountId(long accountId);

    @Query("select tx from Transaction tx where tx.txTime >= :dateFrom and tx.txTime <= :dateTo")
    List<Transaction> findTxListByPeriod(Date dateFrom, Date dateTo);

    @Query("select tx from Transaction tx where tx.txTime >= :dateFrom and tx.txTime <= :dateTo " +
            "and tx.accountIdFrom= :accId or tx.accountIdTo = :accId" )
    List<Transaction> findTxInPeriod(Date dateFrom, Date dateTo, Long accId);
}
