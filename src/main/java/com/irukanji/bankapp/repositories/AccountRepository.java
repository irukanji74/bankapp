package com.irukanji.bankapp.repositories;

import com.irukanji.bankapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
