package com.stylescoder.service;

import com.stylescoder.dto.PedidoRequestDTO;
import com.stylescoder.dto.PedidoResponseDTO;
import com.stylescoder.entity.*;
import com.stylescoder.mapper.PedidoMapper;
import com.stylescoder.repository.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Pedido criarPedido(PedidoRequestDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(dto.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            return null;
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuarioOpt.get());
        pedido.setTipoPagamento(dto.getTipoPagamento());
        pedido.setObservacoes(dto.getObservacoes());
        pedido.setDataVenda(dto.getDataVenda());

        List<Carrinho> itens = dto.getItens().stream().map(itemDTO -> {
            Optional<Produto> produtoOpt = produtoRepository.findById(itemDTO.getProdutoId());
            if (produtoOpt.isEmpty()) {
                return null;
            }

            Carrinho carrinho = new Carrinho();
            carrinho.setPedido(pedido);
            carrinho.setProduto(produtoOpt.get());
            carrinho.setQuantidade(itemDTO.getQuantidade());
            carrinho.setValorUnitario(itemDTO.getValorUnitario());
            return carrinho;
        }).filter(c -> c != null).collect(Collectors.toList());

        pedido.setItens(itens);
        return pedidoRepository.save(pedido);
    }

    public List<PedidoResponseDTO> listarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(PedidoMapper::toDTO).collect(Collectors.toList());
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}
