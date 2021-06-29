package io.emiliocalvet.algamoney_api.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import io.emiliocalvet.algamoney_api.event.RecursoCriadoEvent;
import io.emiliocalvet.algamoney_api.model.Categoria;
import io.emiliocalvet.algamoney_api.repository.CategoriaRepository;

@Service
public class CategoriaService {

  @Autowired
  private ApplicationEventPublisher publisher;

  @Autowired
  CategoriaRepository categoriaRepository;

  public List<Categoria> listar() {
    return categoriaRepository.findAll();
  }

  public Categoria buscarPeloCodigo(Long codigo) {
    return categoriaRepository.findById(codigo).orElse(null);
  }

  public Categoria criar(Categoria categoria, HttpServletResponse response) {
    Categoria categoriaSalva = categoriaRepository.save(categoria);
    publisher.publishEvent(new RecursoCriadoEvent(this, response, categoria.getCodigo()));
    return categoriaSalva;
  }

  public void removePeloCodigo(Long codigo) {
    categoriaRepository.deleteById(codigo);
  }
}
