package com.app.AeropuertoSauceViejo.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.AeropuertoSauceViejo.Entitys.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
