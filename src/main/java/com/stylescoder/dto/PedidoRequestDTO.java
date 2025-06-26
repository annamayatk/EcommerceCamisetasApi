package com.stylescoder.dto;

import java.time.LocalDate;
import java.util.List;

public class PedidoRequestDTO {

    private String tipoPagamento;
    private String observacoes;
    private LocalDate dataVenda;
    private Long usuarioId;
    private List<CarrinhoRequestDTO> itens;

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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<CarrinhoRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<CarrinhoRequestDTO> itens) {
        this.itens = itens;
    }
}
