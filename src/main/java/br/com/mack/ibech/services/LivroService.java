package br.com.mack.ibech.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mack.ibech.domain.Livro;
import br.com.mack.ibech.domain.dtos.LivroDTO;
import br.com.mack.ibech.repositories.LivroRepository;
import br.com.mack.ibech.services.exceptions.ObjectNotFoundException;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;

	public Livro findById(Integer id) {
		Optional<Livro> liv = livroRepository.findById(id);
		return liv.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado! Id: " + id));
	}

	public List<Livro> findAll() {
		return livroRepository.findAll();
	}

	public Livro create(@Valid LivroDTO dto) {
		dto.setId(null);
		Livro liv = new Livro(dto);
		return livroRepository.save(liv);	}

	public Livro update(Integer id, @Valid LivroDTO dto) {
		dto.setId(id);
		Livro oldObj = this.findById(id);
		oldObj = new Livro(dto);
		return livroRepository.save(oldObj);
		}

	public void delete(Integer id) {
		Livro obj = this.findById(id);
		livroRepository.delete(obj);		
	}

}
