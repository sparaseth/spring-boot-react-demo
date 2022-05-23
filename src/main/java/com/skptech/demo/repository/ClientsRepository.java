package com.skptech.demo.repository;

import com.skptech.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Client, Long> {
}
