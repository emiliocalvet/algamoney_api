package io.emiliocalvet.algamoney_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.emiliocalvet.algamoney_api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
  
}
