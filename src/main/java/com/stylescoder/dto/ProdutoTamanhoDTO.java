package com.stylescoder.dto;

public class ProdutoTamanhoDTO {

    private Long tamanhoId;
    private Integer estoque;

    public ProdutoTamanhoDTO() {}

    public ProdutoTamanhoDTO(Long tamanhoId, Integer estoque) {
        this.tamanhoId = tamanhoId;
        this.estoque = estoque;
    }

	public Long getTamanhoId() {
		return tamanhoId;
	}

	public void setTamanhoId(Long tamanhoId) {
		this.tamanhoId = tamanhoId;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}


}