package com.app.TP_DESI2023.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.TP_DESI2023.Entitys.Vuelo;


@Repository
public interface VueloRepository extends JpaRepository<Vuelo, String> {
	
	 List<Vuelo> findAll();

}
