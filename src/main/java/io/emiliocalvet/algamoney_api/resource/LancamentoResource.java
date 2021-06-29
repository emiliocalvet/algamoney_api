package io.emiliocalvet.algamoney_api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.emiliocalvet.algamoney_api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import io.emiliocalvet.algamoney_api.model.Lancamento;
import io.emiliocalvet.algamoney_api.repository.filter.LancamentoFilter;
import io.emiliocalvet.algamoney_api.service.LancamentoService;
import io.emiliocalvet.algamoney_api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
  
  @Autowired
  private LancamentoService lancamentoService;

  @Autowired
  private MessageSource messageSource;

  @GetMapping
  public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
    return lancamentoService.filtrar(lancamentoFilter, pageable);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo){
    Lancamento lancamento = lancamentoService.buscarPeloCodigo(codigo);
    return ResponseEntity.ok(lancamento);
  }

  @PostMapping
  public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
    Lancamento lancamentoSalvo = lancamentoService.criar(lancamento, response);
    return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<?> remover(@PathVariable Long codigo) {
    lancamentoService.remover(codigo);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @ExceptionHandler({ PessoaInexistenteOuInativaException.class })
  public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
    String mensagemUsuario = messageSource.getMessage("pessoa.inativa-ou-inexistente", null, LocaleContextHolder.getLocale());
    String mensagemDesenvolvedor = ex.toString();
    List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
    return ResponseEntity.badRequest().body(erros);
  } 
}
