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

import br.com.mack.ibech.domain.Cliente;
import br.com.mack.ibech.domain.Livro;
import br.com.mack.ibech.domain.dtos.ClienteDTO;
import br.com.mack.ibech.domain.dtos.LivroDTO;
import br.com.mack.ibech.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LivroDTO> findById(@PathVariable Integer id) {
		Livro cli = this.livroService.findById(id);
		return ResponseEntity.ok().body(new LivroDTO(cli));
	}

	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(){
		List<Livro> list = this.livroService.findAll();
		List<LivroDTO> listDTO = list.stream().map(l -> new LivroDTO(l)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<LivroDTO> create(@Valid @RequestBody LivroDTO dto){
		Livro obj = this.livroService.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LivroDTO> update(@PathVariable Integer id, @Valid @RequestBody LivroDTO dto){	
		Livro obj = this.livroService.update(id, dto);
		return ResponseEntity.ok().body(new LivroDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LivroDTO> delete(@PathVariable Integer id){
		this.livroService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
