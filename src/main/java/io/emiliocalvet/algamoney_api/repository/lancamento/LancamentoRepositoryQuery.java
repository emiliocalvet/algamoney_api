package io.emiliocalvet.algamoney_api.repository.lancamento;

import java.util.List;

import io.emiliocalvet.algamoney_api.model.Lancamento;
import io.emiliocalvet.algamoney_api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
  
  public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
