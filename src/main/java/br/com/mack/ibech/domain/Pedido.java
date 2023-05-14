package br.com.mack.ibech.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.mack.ibech.domain.dtos.PedidoDTO;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	private Double valor;
	private LocalDate dataPedido = LocalDate.now();
	
	public Pedido() {
	}
	
	public Pedido (Integer id, Cliente cliente, Double valor, LocalDate dataPedido) {
		this.id = id;
		this.cliente = cliente;
		this.valor = valor;
		this.dataPedido = dataPedido;
	}
	
	public Pedido (PedidoDTO dto, Cliente cliente) {
		this.id = dto.getId();
		this.cliente = cliente;
		this.valor = dto.getValor();
		this.dataPedido = dto.getDataPedido();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

}
