package br.com.mack.ibech.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.mack.ibech.domain.dtos.LivroDTO;

@Entity
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String autor;
	@Column(length=400)
	private String descricao;
	private Double preco;
	
	public Livro() {
		super();
	}
	
	public Livro(Integer id, String titulo, String autor, String descricao, Double preco) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Livro(LivroDTO dto) {
		this.id = dto.getId();
		this.titulo = dto.getTitulo();
		this.autor = dto.getAutor();
		this.descricao = dto.getDescricao();
		this.preco = dto.getPreco();
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
