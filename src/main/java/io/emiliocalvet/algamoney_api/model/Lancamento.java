package io.emiliocalvet.algamoney_api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "lancamento")
public class Lancamento {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  private String descricao;

  @Column(name = "data_vencimento")
  @JsonFormat(pattern =  "dd/MM/yyyy")
  private LocalDate dataVencimento;

  @Column(name = "data_pagamento")
  @JsonFormat(pattern =  "dd/MM/yyyy")
  private LocalDate dataPagamento;

  private BigDecimal valor;

  private String obseracao;

  @Enumerated(EnumType.STRING)
  private TipoLancamento tipo;

  @ManyToOne
  @JoinColumn(name = "codigo_categoria")
  private Categoria categoria;

  @ManyToOne
  @JoinColumn(name = "codigo_pessoa")
  private Pessoa pessoa;

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

  public LocalDate getDataVencimento() {
    return this.dataVencimento;
  }

  public void setDataVencimento(LocalDate dataVencimento) {
    this.dataVencimento = dataVencimento;
  }

  public LocalDate getDataPagamento() {
    return this.dataPagamento;
  }

  public void setDataPagamento(LocalDate dataPagamento) {
    this.dataPagamento = dataPagamento;
  }

  public BigDecimal getValor() {
    return this.valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public String getObseracao() {
    return this.obseracao;
  }

  public void setObseracao(String obseracao) {
    this.obseracao = obseracao;
  }

  public TipoLancamento getTipo() {
    return this.tipo;
  }

  public void setTipo(TipoLancamento tipo) {
    this.tipo = tipo;
  }

  public Categoria getCategoria() {
    return this.categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Pessoa getPessoa() {
    return this.pessoa;
  }

  public void setPessoa(Pessoa pessoa) {
    this.pessoa = pessoa;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Lancamento)) {
            return false;
        }
        Lancamento lancamento = (Lancamento) o;
        return Objects.equals(codigo, lancamento.codigo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo);
  }

}
