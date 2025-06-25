package com.stylescoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylescoder.dto.ProdutoDTO;
import com.stylescoder.entity.Produto;
import com.stylescoder.entity.Tamanho;
import com.stylescoder.repository.ProdutoRepository;
import com.stylescoder.repository.TamanhoRepository;

@Service
public class ProdutoService {

	@Autowired
	public ProdutoRepository produtoRepository;

	@Autowired
	public TamanhoRepository tamanhoRepository;

	public List<ProdutoDTO> listarTodos() {
		List<Produto> produto = produtoRepository.findAll();
		return produto.stream().map(ProdutoDTO::new).toList();
	}

	public void inserir(ProdutoDTO produtos) {
		List<Tamanho> tamanhos = tamanhoRepository.findAll();
		Produto produto = new Produto(produtos, tamanhos);
		produtoRepository.save(produto);
	}

	public ProdutoDTO alterar(ProdutoDTO produtos) {
		List<Tamanho> tamanhos = tamanhoRepository.findAll();
		Produto produto = new Produto(produtos, tamanhos);
		return new ProdutoDTO(produtoRepository.save(produto));
	}

	public void excluir(Long id) {
		Produto produto = produtoRepository.findById(id).orElseThrow();
		produtoRepository.delete(produto);
	}
}