package com.stylescoder.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.stylescoder.entity.Produto;
import com.stylescoder.enums.Categoria;

public class ProdutoDTO {
	
	private Long id;
	private String descricao;
	private Integer quantidade;
	private BigDecimal valor;
	private Categoria categoria;
	
	public ProdutoDTO(Produto produto) {
		BeanUtils.copyProperties(produto, this);
	}

	public ProdutoDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
