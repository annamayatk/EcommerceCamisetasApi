package com.stylescoder.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.stylescoder.dto.ProdutoDTO;
import com.stylescoder.enums.Categoria;

import jakarta.persistence.*;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String imagem;
	private String descricao;
	private Integer quantidade;
	private BigDecimal valor;
	private Categoria categoria;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProdutoTamanho> tamanhos;

	public Produto() {
	}

	public Produto(ProdutoDTO dto, List<Tamanho> tamanhosDisponiveis) {
		this.id = dto.getId();
		this.imagem = dto.getImagem();
		this.descricao = dto.getDescricao();
		this.quantidade = dto.getQuantidade();
		this.valor = dto.getValor();
		this.categoria = dto.getCategoria();

		this.tamanhos = dto.getTamanhos().stream().map(dtoT -> {
			Tamanho tamanho = tamanhosDisponiveis.stream().filter(t -> t.getId().equals(dtoT.getTamanhoId()))
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Tamanho não encontrado: " + dtoT.getTamanhoId()));

			ProdutoTamanho pt = new ProdutoTamanho();
			pt.setProduto(this);
			pt.setTamanho(tamanho);
			pt.setEstoque(dtoT.getEstoque());

			return pt;
		}).toList();
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

	public List<ProdutoTamanho> getTamanhos() {
		return tamanhos;
	}

	public void setTamanhos(List<ProdutoTamanho> tamanhos) {
		this.tamanhos = tamanhos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, descricao, id, imagem, quantidade, tamanhos, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return categoria == other.categoria && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && Objects.equals(imagem, other.imagem)
				&& Objects.equals(quantidade, other.quantidade) && Objects.equals(tamanhos, other.tamanhos)
				&& Objects.equals(valor, other.valor);
	}

}