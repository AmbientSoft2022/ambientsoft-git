package com.thymeleaf.TallerThymeleaf.repository;

import com.thymeleaf.TallerThymeleaf.modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoDineroRepositorio extends JpaRepository<MovimientoDinero, Long>{
     List<MovimientoDinero> findByNit(Long nit);
}
