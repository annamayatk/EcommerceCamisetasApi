package com.stylescoder.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.stylescoder.dto.UsuarioRequestDTO;
import com.stylescoder.dto.UsuarioResponseDTO;
import com.stylescoder.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Map<String, Object>> inserir(@Valid @RequestBody UsuarioRequestDTO dto) {
	    UsuarioResponseDTO usuarioCriado = usuarioService.inserir(dto);

	    Map<String, Object> resposta = new HashMap<>();
	    resposta.put("mensagem", "Usuário criado com sucesso!");
	    resposta.put("usuario", usuarioCriado);

	    return ResponseEntity.status(201).body(resposta); 
	}


	@GetMapping
	public ResponseEntity<Map<String, Object>> listarTodos() {
	    List<UsuarioResponseDTO> usuarios = usuarioService.listarTodos();

	    Map<String, Object> resposta = new HashMap<>();

	    if (usuarios.isEmpty()) {
	        resposta.put("mensagem", "Não há usuários cadastrados.");
	        return ResponseEntity.ok(resposta);
	    }

	    resposta.put("mensagem", "Lista de usuários cadastrados... ");
	    resposta.put("usuarios", usuarios);
	    return ResponseEntity.ok(resposta);
	}


	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
		UsuarioResponseDTO usuario = usuarioService.buscarPorId(id);
		return ResponseEntity.ok(usuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> atualizar(@PathVariable Long id,
			@Valid @RequestBody UsuarioRequestDTO dto) {

		UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizar(id, dto);

		Map<String, Object> resposta = new HashMap<>();
		resposta.put("mensagem", "Usuário atualizado com sucesso!");
		resposta.put("usuario", usuarioAtualizado);

		return ResponseEntity.ok(resposta);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		usuarioService.deletar(id);
		return ResponseEntity.ok("Usuário deletado com sucesso.");
	}

}
