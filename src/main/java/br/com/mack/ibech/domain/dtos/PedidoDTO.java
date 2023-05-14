package br.com.mack.ibech.domain.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.mack.ibech.domain.Cliente;
import br.com.mack.ibech.domain.Pedido;

public class PedidoDTO {
	
	private Integer id;
	private Integer cliente;
	private String nomeCliente;
	private Double valor;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPedido = LocalDate.now();
	
	public PedidoDTO() {
	}
	
	public PedidoDTO(Integer id, Cliente cliente, Double valor, LocalDate dataPedido) {
		this.id = id;
		this.cliente = cliente.getId();
		this.valor = valor;
		this.dataPedido = dataPedido;
	}
	
	public PedidoDTO (Pedido pedido) {
		this.id = pedido.getId();
		this.cliente = pedido.getCliente().getId();
		this.nomeCliente = pedido.getCliente().getNome();
		this.valor = pedido.getValor();
		this.dataPedido = pedido.getDataPedido();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCliente() {
		return cliente;
	}
	
	public void setCliente(Integer cliente) {
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
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
}
