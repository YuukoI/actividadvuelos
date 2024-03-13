package com.app.AeropuertoSauceViejo.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.AeropuertoSauceViejo.Entitys.Vuelo;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, String> {

	List<Vuelo> findAll();

}
