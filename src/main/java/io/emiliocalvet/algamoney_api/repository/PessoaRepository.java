package io.emiliocalvet.algamoney_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.emiliocalvet.algamoney_api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
  
}
