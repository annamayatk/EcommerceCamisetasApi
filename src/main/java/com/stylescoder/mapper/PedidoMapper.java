package com.stylescoder.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.stylescoder.dto.CarrinhoResponseDTO;
import com.stylescoder.dto.PedidoResponseDTO;
import com.stylescoder.dto.ProdutoResponseDTO;
import com.stylescoder.dto.UsuarioResponseDTO;
import com.stylescoder.entity.Carrinho;
import com.stylescoder.entity.Pedido;
import com.stylescoder.entity.Produto;
import com.stylescoder.entity.Usuario;
/**
 * Classe utilitária responsável por converter a entidade Pedido e seus relacionamentos
 * (como Carrinho, Produto e Usuario) para os respectivos DTOs de resposta (ResponseDTO),
 * que são usados na comunicação com o front-end.
 *
 * Evita que dados sensíveis ou desnecessários sejam expostos, garantindo um formato
 * mais enxuto, seguro e adequado para exibição na API.
 *
 */
public class PedidoMapper {

	public static PedidoResponseDTO toDTO(Pedido pedido) {
		PedidoResponseDTO dto = new PedidoResponseDTO();
		dto.setId(pedido.getId());
		dto.setTipoPagamento(pedido.getTipoPagamento());
		dto.setObservacoes(pedido.getObservacoes());
		dto.setDataVenda(pedido.getDataVenda());
		dto.setUsuario(toUsuarioResponseDTO(pedido.getUsuario()));

		List<CarrinhoResponseDTO> itens = pedido.getItens().stream().map(PedidoMapper::toCarrinhoResponseDTO)
				.collect(Collectors.toList());

		dto.setItens(itens);
		return dto;
	}

	private static UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {
		UsuarioResponseDTO dto = new UsuarioResponseDTO();
		dto.setId(usuario.getId());
		dto.setNome(usuario.getNome());
		dto.setEmail(usuario.getEmail());
		return dto;
	}

	private static CarrinhoResponseDTO toCarrinhoResponseDTO(Carrinho carrinho) {
		CarrinhoResponseDTO dto = new CarrinhoResponseDTO();
		dto.setId(carrinho.getId());
		dto.setQuantidade(carrinho.getQuantidade());
		dto.setValorUnitario(carrinho.getValorUnitario());
		dto.setProduto(new ProdutoResponseDTO(carrinho.getProduto()));

		return dto;
	}

}
