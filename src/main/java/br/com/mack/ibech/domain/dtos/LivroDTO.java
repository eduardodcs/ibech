package br.com.mack.ibech.domain.dtos;

import javax.validation.constraints.NotNull;

import br.com.mack.ibech.domain.Livro;

public class LivroDTO {
	
	private Integer id;
	@NotNull(message = "O título é requerido!")
	private String titulo;
	@NotNull(message = "O autor é requerido!")
	private String autor;
	private String descricao;
	private Double preco;
	
	public LivroDTO() {
		super();
	}
	
	public LivroDTO(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.autor = livro.getAutor();
		this.descricao = livro.getDescricao();
		this.preco = livro.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
