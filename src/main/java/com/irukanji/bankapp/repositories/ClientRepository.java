package com.irukanji.bankapp.repositories;

import com.irukanji.bankapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where c.name = :name")
    Client findByName(@Param("name") String name);
}
