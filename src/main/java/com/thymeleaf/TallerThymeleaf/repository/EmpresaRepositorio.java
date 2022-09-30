package com.thymeleaf.TallerThymeleaf.repository;

import com.thymeleaf.TallerThymeleaf.modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa, Long>{

}
