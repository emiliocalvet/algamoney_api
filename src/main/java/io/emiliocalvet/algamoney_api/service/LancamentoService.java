package io.emiliocalvet.algamoney_api.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.emiliocalvet.algamoney_api.event.RecursoCriadoEvent;
import io.emiliocalvet.algamoney_api.model.Lancamento;
import io.emiliocalvet.algamoney_api.model.Pessoa;
import io.emiliocalvet.algamoney_api.repository.LancamentoRepository;
import io.emiliocalvet.algamoney_api.repository.PessoaRepository;
import io.emiliocalvet.algamoney_api.repository.filter.LancamentoFilter;
import io.emiliocalvet.algamoney_api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
  
  @Autowired
  private LancamentoRepository lancamentoRepository;

  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private ApplicationEventPublisher publisher;

  public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
    return lancamentoRepository.filtrar(lancamentoFilter);
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
}
