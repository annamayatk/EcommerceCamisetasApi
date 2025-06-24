package com.stylescoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stylescoder.dto.ProdutoDTO;
import com.stylescoder.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<ProdutoDTO> listarTodos() {
		return produtoService.listarTodos();
	}

	@PostMapping
	public void inserir(@Valid @RequestBody ProdutoDTO produtos) {
		produtoService.inserir(produtos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDTO> alterar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtos) {
		produtos.setId(id);
		ProdutoDTO atualizado = produtoService.alterar(produtos);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		produtoService.excluir(id);
		return ResponseEntity.ok().build();
	}

}
