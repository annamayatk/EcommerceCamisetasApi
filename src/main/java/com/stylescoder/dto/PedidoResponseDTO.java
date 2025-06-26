package com.stylescoder.dto;

import java.time.LocalDate;
import java.util.List;

public class PedidoResponseDTO {

    private Long id;
    private String tipoPagamento;
    private String observacoes;
    private LocalDate dataVenda;
    private UsuarioResponseDTO usuario;
    private List<CarrinhoResponseDTO> itens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }

    public List<CarrinhoResponseDTO> getItens() {
        return itens;
    }

    public void setItens(List<CarrinhoResponseDTO> itens) {
        this.itens = itens;
    }
}
