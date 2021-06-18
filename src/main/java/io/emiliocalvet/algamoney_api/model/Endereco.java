package io.emiliocalvet.algamoney_api.model;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {
  
  @NotNull
  @Size(min = 3, max = 50)
  private String logradouro;

  @NotNull
  private Integer numero;

  @Size(min = 3, max = 30)
  private String complemento;
  
  @Size(min = 3, max = 10)
  private String cep;
  
  @Size(min = 3, max = 30)
  private String bairro;
  
  @Size(min = 3, max = 20)
  private String cidade;
  
  @Size(min = 3, max = 20)
  private String estado;

  
  public String getLogradouro() {
    return this.logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public Integer getNumero() {
    return this.numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return this.complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getCep() {
    return this.cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getBairro() {
    return this.bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return this.cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return this.estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }


  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Endereco)) {
            return false;
        }
        Endereco endereco = (Endereco) o;
        return Objects.equals(logradouro, endereco.logradouro) && Objects.equals(numero, endereco.numero) && Objects.equals(complemento, endereco.complemento) && Objects.equals(cep, endereco.cep) && Objects.equals(bairro, endereco.bairro) && Objects.equals(cidade, endereco.cidade) && Objects.equals(estado, endereco.estado);
  }

  @Override
  public int hashCode() {
    return Objects.hash(logradouro, numero, complemento, cep, bairro, cidade, estado);
  }


}
