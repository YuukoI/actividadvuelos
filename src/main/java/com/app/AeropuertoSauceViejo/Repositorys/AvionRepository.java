package com.app.AeropuertoSauceViejo.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.AeropuertoSauceViejo.Entitys.Avion;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Long> {

}
