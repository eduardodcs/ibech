package br.com.mack.ibech.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.mack.ibech.domain.Pedido;
import br.com.mack.ibech.domain.dtos.PedidoDTO;
import br.com.mack.ibech.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> findById(@PathVariable Integer id) {
		Pedido cli = this.pedidoService.findById(id);
		return ResponseEntity.ok().body(new PedidoDTO(cli));
	}

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll(){
		List<Pedido> list = this.pedidoService.findAll();
		List<PedidoDTO> listDTO = list.stream().map(c -> new PedidoDTO(c)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<PedidoDTO> create(@Valid @RequestBody PedidoDTO dto){
		Pedido obj = this.pedidoService.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> update(@PathVariable Integer id, @Valid @RequestBody PedidoDTO dto){	
		Pedido obj = this.pedidoService.update(id, dto);
		return ResponseEntity.ok().body(new PedidoDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> delete(@PathVariable Integer id){
		this.pedidoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
