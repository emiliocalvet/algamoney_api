package io.emiliocalvet.algamoney_api.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
public class Permissao {
  
  @Id
  private Long codigo;

  @Column
  private String descricao;

  public Long getCodigo() { 
    return this.codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Permissao)) {
            return false;
        }
        Permissao permissao = (Permissao) o;
        return Objects.equals(codigo, permissao.codigo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo);
  }

}
