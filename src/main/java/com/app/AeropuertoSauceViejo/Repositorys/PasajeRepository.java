package com.app.AeropuertoSauceViejo.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.AeropuertoSauceViejo.Entitys.Pasaje;

@Repository
public interface PasajeRepository extends JpaRepository<Pasaje, Long> {

}
