package com.thymeleaf.TallerThymeleaf.repository;

import com.thymeleaf.TallerThymeleaf.modelos.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {

}
