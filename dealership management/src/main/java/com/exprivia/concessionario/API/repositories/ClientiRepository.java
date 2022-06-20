package com.exprivia.concessionario.API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exprivia.concessionario.API.model.Cliente;

public interface ClientiRepository extends JpaRepository<Cliente, String> {

}
