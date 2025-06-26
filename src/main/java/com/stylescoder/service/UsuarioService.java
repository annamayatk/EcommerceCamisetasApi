package com.stylescoder.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylescoder.dto.UsuarioRequestDTO;
import com.stylescoder.dto.UsuarioResponseDTO;
import com.stylescoder.entity.Endereco;
import com.stylescoder.entity.Usuario;
import com.stylescoder.exception.UsuarioException;
import com.stylescoder.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EnderecoService enderecoService;

	public List<UsuarioResponseDTO> listarTodos() {
		return usuarioRepository.findAll().stream()
				.map(this::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Transactional
	public UsuarioResponseDTO inserir(UsuarioRequestDTO dto) {
		Optional<Usuario> existente = usuarioRepository.findByEmail(dto.getEmail());
		if (existente.isPresent()) {
			throw new UsuarioException("E-mail já cadastrado.");
		}

		Endereco endereco = enderecoService.buscarEnderecoPorCep(dto.getCep());

		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setCpf(dto.getCpf());
		usuario.setDataNasc(dto.getDataNasc());
		usuario.setCelular(dto.getCelular());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());
		usuario.setDataCadastro(LocalDate.now());
		usuario.setEndereco(endereco);

		usuarioRepository.save(usuario);

		return toResponseDTO(usuario);
	}

	@Transactional
	public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioException("Usuário não encontrado."));

		Optional<Usuario> outro = usuarioRepository.findByEmail(dto.getEmail());
		if (outro.isPresent() && !outro.get().getId().equals(id)) {
			throw new UsuarioException("Este e-mail já está em uso por outro usuário.");
		}

		Endereco endereco = enderecoService.buscarEnderecoPorCep(dto.getCep());

		usuario.setNome(dto.getNome());
		usuario.setCpf(dto.getCpf());
		usuario.setDataNasc(dto.getDataNasc());
		usuario.setCelular(dto.getCelular());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());
		usuario.setEndereco(endereco);

		usuarioRepository.save(usuario);

		return toResponseDTO(usuario);
	}

	public UsuarioResponseDTO buscarPorId(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioException("Usuário não encontrado."));
		return toResponseDTO(usuario);
	}

	public void deletar(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioException("Usuário não encontrado."));
		usuarioRepository.delete(usuario);
	}

	private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
		UsuarioResponseDTO dto = new UsuarioResponseDTO();
		dto.setId(usuario.getId());
		dto.setNome(usuario.getNome());
		dto.setEmail(usuario.getEmail());
		dto.setCelular(usuario.getCelular());
		dto.setDataCadastro(usuario.getDataCadastro());
		dto.setEndereco(usuario.getEndereco());
		return dto;
	}
}
