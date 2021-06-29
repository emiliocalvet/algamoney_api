package io.emiliocalvet.algamoney_api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.emiliocalvet.algamoney_api.model.Categoria;
import io.emiliocalvet.algamoney_api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

  @Autowired
  private CategoriaService categoriaService;

  @GetMapping
  public List<Categoria> listar() {
    return categoriaService.listar();
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
    Categoria categoriaBuscada = categoriaService.buscarPeloCodigo(codigo);
    return categoriaBuscada != null ? ResponseEntity.ok(categoriaBuscada) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
    Categoria categoriaSalva = categoriaService.criar(categoria, response);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
  }

  @DeleteMapping("/{codigo}")
  public void removerPeloCodigo(@PathVariable Long codigo) {
    categoriaService.removePeloCodigo(codigo);
  }
}
