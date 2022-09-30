package com.thymeleaf.TallerThymeleaf.repository;

import com.thymeleaf.TallerThymeleaf.modelos.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Anotacion que le dice a Spring que esta clase es un repositorio
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {
}
