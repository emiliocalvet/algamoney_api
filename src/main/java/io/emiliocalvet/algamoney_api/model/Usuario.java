package io.emiliocalvet.algamoney_api.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
  
  @Id
  private Long codigo;

  @Column
  private String nome;

  @Column
  private String email;

  @Column 
  private String senha;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "codigo_usuario"),
    inverseJoinColumns = @JoinColumn(name = "codigo_permissao"))
  private List<Permissao> permissoes;

  public Long getCodigo() {
    return this.codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public List<Permissao> getPermissoes() {
    return this.permissoes;
  }

  public void setPermissoes(List<Permissao> permissoes) {
    this.permissoes = permissoes;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(codigo, usuario.codigo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo);
  }

}
