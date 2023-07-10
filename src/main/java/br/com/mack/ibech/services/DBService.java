package br.com.mack.ibech.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.mack.ibech.domain.Cliente;
import br.com.mack.ibech.domain.Fornecedor;
import br.com.mack.ibech.repositories.ClienteRepository;
import br.com.mack.ibech.repositories.FornecedorRepository;

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instaciaDB() {
	    Cliente cli1 = new Cliente(null, "christin", "14868316052", "admin@gmail.com", encoder.encode("1234"));
	    clienteRepository.save(cli1);

	    Fornecedor forn1 = new Fornecedor(null, "jaimilton", "66162132000128", "admin1@gmail.com");
	    forn1.setSenha(encoder.encode("12345"));
	    fornecedorRepository.save(forn1);
	}
	
}
