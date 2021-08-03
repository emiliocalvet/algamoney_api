package io.emiliocalvet.algamoney_api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.emiliocalvet.algamoney_api.model.Lancamento;
import io.emiliocalvet.algamoney_api.repository.filter.LancamentoFilter;
import io.emiliocalvet.algamoney_api.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
  
  public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

  public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
