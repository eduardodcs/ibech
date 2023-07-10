package br.com.mack.ibech.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.mack.ibech.domain.Cliente;
import br.com.mack.ibech.domain.dtos.ClienteDTO;
import br.com.mack.ibech.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
//ou seja, receber as requisições HTTP, direcioná-las
//para o serviço apropriado e retornar as respostas adequadas.
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		Cliente cli = this.clienteService.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(cli));
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> list = this.clienteService.findAll();
		List<ClienteDTO> listDTO = list.stream().map(c -> new ClienteDTO(c)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO dto){
		Cliente obj = this.clienteService.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/clientes/{id}/update")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO dto) {
		return null;
	    // Implementação do método
	}

	@DeleteMapping("/clientes/{id}/delete")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		return null;
	    // Implementação do método
	}

	@PutMapping("/{id}/ponto")
	public ResponseEntity<Void> baterPonto(@PathVariable Integer id) {
	    clienteService.baterPonto(id);
	    return ResponseEntity.noContent().build();
	}
	@PostMapping("/login")
	public ResponseEntity<ClienteDTO> login(@RequestParam String email, @RequestParam String senha) {
	    Cliente cliente = clienteService.findByEmailAndSenha(email, senha);
	    if (cliente != null) {
	        // Login bem-sucedido, retornar dados do cliente ou token JWT
	        ClienteDTO clienteDTO = new ClienteDTO(cliente);
	        return ResponseEntity.ok().body(clienteDTO);
	    } else {
	        // Credenciais inválidas, retornar erro de autenticação
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}

	


}

