package com.dev.multipledb.repositories.client;

import com.dev.multipledb.entities.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
