package io.emiliocalvet.algamoney_api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping
  public List<Pessoa> listar() {
    return pessoaRepository.findAll();
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
    Pessoa pessoaBuscada = pessoaRepository.findById(codigo).orElse(null);
    return pessoaBuscada != null ? ResponseEntity.ok(pessoaBuscada) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<?> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
    Pessoa pessoaSalva = pessoaRepository.save(pessoa);
    publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
    return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
  }

  @DeleteMapping("/{codigo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removerPeloCodigo(@PathVariable Long codigo) {
    pessoaRepository.deleteById(codigo);
  }
}
