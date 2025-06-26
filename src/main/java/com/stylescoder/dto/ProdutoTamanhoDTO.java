package com.stylescoder.dto;

public class ProdutoTamanhoDTO {

    private Long tamanhoId;
    private String variacao;
    private Integer estoque;

    public ProdutoTamanhoDTO() {}

    public ProdutoTamanhoDTO(Long tamanhoId, String variacao, Integer estoque) {
        this.tamanhoId = tamanhoId;
        this.variacao = variacao;
        this.estoque = estoque;
    }



    public Long getTamanhoId() {
        return tamanhoId;
    }

    public void setTamanhoId(Long tamanhoId) {
        this.tamanhoId = tamanhoId;
    }

    public String getVariacao() {
        return variacao;
    }

    public void setVariacao(String variacao) {
        this.variacao = variacao;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}
