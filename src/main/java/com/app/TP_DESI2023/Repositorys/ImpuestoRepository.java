package com.app.TP_DESI2023.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.TP_DESI2023.Entitys.Impuesto;

@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, Long> {

}
