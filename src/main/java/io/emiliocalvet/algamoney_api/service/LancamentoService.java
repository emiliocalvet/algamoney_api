package io.emiliocalvet.algamoney_api.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.emiliocalvet.algamoney_api.event.RecursoCriadoEvent;
import io.emiliocalvet.algamoney_api.model.Lancamento;
import io.emiliocalvet.algamoney_api.model.Pessoa;
import io.emiliocalvet.algamoney_api.repository.LancamentoRepository;
import io.emiliocalvet.algamoney_api.repository.PessoaRepository;
import io.emiliocalvet.algamoney_api.repository.filter.LancamentoFilter;
import io.emiliocalvet.algamoney_api.repository.projection.ResumoLancamento;
import io.emiliocalvet.algamoney_api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

  @Autowired
  private LancamentoRepository lancamentoRepository;

  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private ApplicationEventPublisher publisher;

  public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
    return lancamentoRepository.filtrar(lancamentoFilter, pageable);
  }

  public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
    return lancamentoRepository.resumir(lancamentoFilter, pageable);
  }

  public Lancamento buscarPeloCodigo(Long codigo) {
    Lancamento lancamento = lancamentoRepository.findById(codigo).orElse(null);
    if (lancamento == null) {
      throw new EmptyResultDataAccessException(1);
    }
    return lancamento;
  }

  public Lancamento criar(Lancamento lancamento, HttpServletResponse response) {
    Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElse(null);
    if (pessoa == null || pessoa.isInativo()) {
      throw new PessoaInexistenteOuInativaException();
    }
    Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
    publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
    return lancamentoSalvo;
  }

  public void remover(Long codigo) {
    lancamentoRepository.deleteById(codigo);
  }
}
