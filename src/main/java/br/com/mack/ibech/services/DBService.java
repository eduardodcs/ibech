package br.com.mack.ibech.services;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mack.ibech.domain.Cliente;
import br.com.mack.ibech.domain.Fornecedor;
import br.com.mack.ibech.domain.Livro;
import br.com.mack.ibech.domain.Pedido;
import br.com.mack.ibech.repositories.ClienteRepository;
import br.com.mack.ibech.repositories.FornecedorRepository;
import br.com.mack.ibech.repositories.LivroRepository;
import br.com.mack.ibech.repositories.PedidoRepository;


@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instaciaDB() {
		
		Cliente cli1 = new Cliente(null, "admin", "14868316052", "admin@gmail.com", encoder.encode("admin"));
		Cliente cli2 = new Cliente(null, "Gilvan Souza", "63570355055", "gilvan@gmail.com", encoder.encode("admin1"));
		Cliente cli3 = new Cliente(null, "Edlaine Cardoso", "00600819027", "edlaine@gmail.com", encoder.encode("admin2"));
		Cliente cli4 = new Cliente(null, "José Joaquim", "54626933033", "jose@gmail.com", encoder.encode("admin3"));
		Cliente cli5 = new Cliente(null, "Avelino Smith", "12167730098", "avelino@gmail.com", encoder.encode("admin4"));
		Cliente cli6 = new Cliente(null, "Vania Costa", "16390698011", "vania@gmail.com", encoder.encode("admin5"));
		Cliente cli7 = new Cliente(null, "Odair José", "31596889098", "odair@gmail.com", encoder.encode("admin6"));
		Cliente cli8 = new Cliente(null, "Bruno Gustavo", "43631310048", "bruno@gmail.com", encoder.encode("admin7"));
		Cliente cli9 = new Cliente(null, "Adélia Silva", "19425656070", "adelia@gmail.com", encoder.encode("admin8"));
		Cliente cli10 = new Cliente(null, "Katia Gonzaga", "76212064032", "katia@gmail.com", encoder.encode("admin9"));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6, cli7, cli8, cli9, cli10));
		
		Fornecedor forn1 = new Fornecedor(null, "Paulista", "66162132000128", "fornecedor01@gmail.com.br");
		Fornecedor forn2 = new Fornecedor(null, "Saraiva", "29497605000111", "fornecedor02@gmail.com.br");
		Fornecedor forn3 = new Fornecedor(null, "Abril", "97521662000149", "fornecedor03@gmail.com.br");
		Fornecedor forn4 = new Fornecedor(null, "Católica", "51772565000128", "fornecedor04@gmail.com.br");
		Fornecedor forn5 = new Fornecedor(null, "Novatec", "80915019000105", "fornecedor05@gmail.com.br");
		Fornecedor forn6 = new Fornecedor(null, "Campus", "56456032000150", "fornecedor06@gmail.com.br");
		Fornecedor forn7 = new Fornecedor(null, "Primeira Pessoa", "32366937000171", "fornecedor07@gmail.com.br");
		Fornecedor forn8 = new Fornecedor(null, "Harvard", "83049934000145", "fornecedor08@gmail.com.br");
		Fornecedor forn9 = new Fornecedor(null, "Editora USP", "17444064000184", "fornecedor09@gmail.com.br");
		Fornecedor forn10 = new Fornecedor(null, "Viseu", "63747687000143", "fornecedor10@gmail.com.br");
		fornecedorRepository.saveAll(Arrays.asList(forn1, forn2, forn3, forn4, forn5, forn6, forn7, forn8, forn9, forn10));
		
		Livro liv01 = new Livro(null, "Como fazer amigos e influenciar pessoas", "Dale Carnegie", 
				"Um dos maiores clássicos de todos os tempos, Como fazer amigos e influenciar pessoas é considerado a Bíblia dos relacionamentos interpessoais.", 
				49.90);
		Livro liv02 = new Livro(null, "A revolução dos bichos: Um conto de fadas", "George Orwell e Heitor Aquino Ferreira",
				"Verdadeiro clássico moderno, concebido por um dos mais influentes escritores do século XX, A revolução dos bichos é uma fábula sobre o poder. "
				+ "Narra a insurreição dos animais de uma granja contra seus donos. Progressivamente, porém, a revolução degenera numa tirania ainda mais opressiva que a dos humanos.",
				37.80);
		this.livroRepository.saveAll(Arrays.asList(liv01, liv02));
		
		Pedido ped01 = new Pedido(null, cli4, 93.50, LocalDate.of(2023, 5, 3));
		this.pedidoRepository.saveAll(Arrays.asList(ped01));
		
		
	}
	
}
