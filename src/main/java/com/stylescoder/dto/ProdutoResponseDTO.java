package com.stylescoder.dto;

import java.math.BigDecimal;

import com.stylescoder.entity.Produto;

public class ProdutoResponseDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private String imagem;

    public ProdutoResponseDTO() {}

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.imagem = produto.getImagem();
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

    
}

