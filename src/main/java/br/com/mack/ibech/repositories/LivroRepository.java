package br.com.mack.ibech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mack.ibech.domain.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> { 

}
