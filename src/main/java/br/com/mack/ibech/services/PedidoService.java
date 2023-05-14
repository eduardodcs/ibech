package br.com.mack.ibech.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mack.ibech.domain.Cliente;
import br.com.mack.ibech.domain.Pedido;
import br.com.mack.ibech.domain.dtos.PedidoDTO;
import br.com.mack.ibech.repositories.PedidoRepository;
import br.com.mack.ibech.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteService clienteService;

	public Pedido findById(Integer id) {
		Optional<Pedido> cli = pedidoRepository.findById(id);
		return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado! Id: " + id));
	}

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	public Pedido create(PedidoDTO dto) {
		dto.setId(null);
		dto.setDataPedido(LocalDate.now());
		Cliente cliente = clienteService.findById(dto.getCliente());
		Pedido cli = new Pedido(dto, cliente);
		return pedidoRepository.save(cli);
	}

	public Pedido update(Integer id, @Valid PedidoDTO dto) {
		dto.setId(id);
		Cliente cliente = clienteService.findById(dto.getCliente());
		Pedido oldObj = this.findById(id);
		oldObj = new Pedido(dto, cliente);
		return pedidoRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Pedido obj = this.findById(id);
		pedidoRepository.delete(obj);
	}


}
