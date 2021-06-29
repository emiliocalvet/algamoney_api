package io.emiliocalvet.algamoney_api.model;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pessoa")
public class Pessoa {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @NotNull
  @Size(min = 3, max = 50)
  private String nome;
  
  @Embedded
  private Endereco endereco;

  @NotNull
  private Boolean ativo;


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

  public Endereco getEndereco() {
    return this.endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  public Boolean isAtivo() {
    return this.ativo;
  }

  public Boolean getAtivo() {
    return this.ativo;
  }

  @JsonIgnore
  @Transient
  public boolean isInativo() {
    return !this.ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }


  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pessoa)) {
            return false;
        }
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(codigo, pessoa.codigo) && Objects.equals(nome, pessoa.nome) && Objects.equals(endereco, pessoa.endereco) && Objects.equals(ativo, pessoa.ativo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nome, endereco, ativo);
  }

}
