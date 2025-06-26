package com.stylescoder.dto;

import java.math.BigDecimal;
import java.util.List;

import com.stylescoder.entity.Produto;
import com.stylescoder.enums.Categoria;

public class ProdutoDTO {

    private Long id;
    private String imagem;
    private String descricao;
    private Integer quantidade;
    private BigDecimal valor;
    private Categoria categoria;
    private List<ProdutoTamanhoDTO> tamanhos;

    public ProdutoDTO() {}

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.imagem = produto.getImagem();
        this.descricao = produto.getDescricao();
        this.quantidade = produto.getQuantidade();
        this.valor = produto.getValor();
        this.categoria = produto.getCategoria();

       
        this.tamanhos = produto.getTamanhos().stream()
            .map(pt -> new ProdutoTamanhoDTO(
                pt.getTamanho().getId(),
                pt.getTamanho().getVariacao(),
                pt.getEstoque()
            )).toList();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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

    public List<ProdutoTamanhoDTO> getTamanhos() {
        return tamanhos;
    }

    public void setTamanhos(List<ProdutoTamanhoDTO> tamanhos) {
        this.tamanhos = tamanhos;
    }
}
