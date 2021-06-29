package io.emiliocalvet.algamoney_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.emiliocalvet.algamoney_api.model.Lancamento;
import io.emiliocalvet.algamoney_api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
  
}
