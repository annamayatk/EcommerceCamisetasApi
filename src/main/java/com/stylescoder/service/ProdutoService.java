package com.stylescoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylescoder.dto.ProdutoDTO;
import com.stylescoder.entity.Produto;
import com.stylescoder.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	public ProdutoRepository produtoRepository;

	public List<ProdutoDTO> listarTodos() {
		List<Produto> produto = produtoRepository.findAll();
		return produto.stream().map(ProdutoDTO::new).toList();
	}

	public void inserir(ProdutoDTO produtos) {
		Produto produto = new Produto(produtos);
		produtoRepository.save(produto);
	}

	public ProdutoDTO alterar(ProdutoDTO produtos) {
		Produto produto = new Produto(produtos);
		return new ProdutoDTO(produtoRepository.save(produto));

	}

	public void excluir(Long id) {
		Produto produto = produtoRepository.findById(id).get();
		produtoRepository.delete(produto);

	}

}
