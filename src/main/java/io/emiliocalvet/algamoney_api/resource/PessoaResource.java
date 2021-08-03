package io.emiliocalvet.algamoney_api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.emiliocalvet.algamoney_api.event.RecursoCriadoEvent;
import io.emiliocalvet.algamoney_api.model.Pessoa;
import io.emiliocalvet.algamoney_api.repository.PessoaRepository;
import io.emiliocalvet.algamoney_api.service.PessoaService;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

  @Autowired
  private PessoaService pessoaService;

  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_PERQUISAR_PESSOA') and #oauth2.hasScope('read')")
  public List<Pessoa> listar() {
    return pessoaRepository.findAll();
  }

  @GetMapping("/{codigo}")
  @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
  public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
    Pessoa pessoaBuscada = pessoaRepository.findById(codigo).orElse(null);
    return pessoaBuscada != null ? ResponseEntity.ok(pessoaBuscada) : ResponseEntity.notFound().build();
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
  public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
    Pessoa pessoaSalva = pessoaRepository.save(pessoa);
    publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
    return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
  }

  @DeleteMapping("/{codigo}")
  @PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removerPeloCodigo(@PathVariable Long codigo) {
    pessoaRepository.deleteById(codigo);
  }

  @PutMapping("/{codigo}")
  @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
  public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
    Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
    return ResponseEntity.ok(pessoaSalva);
  }

  @PutMapping("/{codigo}/ativo")
  @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
  public ResponseEntity<?> atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
    pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
