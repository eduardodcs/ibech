package br.com.mack.ibech.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mack.ibech.domain.Cliente;
import br.com.mack.ibech.domain.dtos.ClienteDTO;
import br.com.mack.ibech.domain.enums.Perfil;
import br.com.mack.ibech.repositories.ClienteRepository;
import br.com.mack.ibech.services.exceptions.ObjectNotFoundException;

//contém a lógica de negócio relacionada aos clientes, como busca, criação, atualização e exclusão de clientes, além de realizar validações e manipular os dados antes de persisti-los no banco de dados.
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	public Cliente findById(Integer id) {
		Optional<Cliente> cli = clienteRepository.findById(id);
		return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente create(ClienteDTO dto) {
		dto.setId(null);
		dto.setSenha(encoder.encode(dto.getSenha()));
		validaPorCpfEEmail(dto);
		Cliente cli = new Cliente(dto);
		return clienteRepository.save(cli);
	}

	private void validaPorCpfEEmail(ClienteDTO dto) {
		Optional<Cliente> cli = clienteRepository.findByCpf(dto.getCpf());
		if (cli.isPresent() && cli.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		cli = clienteRepository.findByEmail(dto.getEmail());
		if (cli.isPresent() && cli.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}
	public void baterPonto(Integer id) {
	    Cliente cliente = findById(id);
	    cliente.setPonto(LocalDate.now());
	    clienteRepository.save(cliente);
	}


	public Cliente update(Integer id, @Valid ClienteDTO dto) {
		dto.setId(id);
		Cliente oldObj = this.findById(id);
		if (!dto.getSenha().equals(oldObj.getSenha())) {
			dto.setSenha(encoder.encode(dto.getSenha()));
		}
		this.validaPorCpfEEmail(dto);
		oldObj = new Cliente(dto);
		return clienteRepository.save(oldObj);
	}

	public void delete(Integer id, String senha) {
		Cliente obj = this.findById(id);
		
		 if (!obj.getPerfis().contains(Perfil.ADMIN)) {
		        throw new RuntimeException("Somente o administrador pode excluir um cliente.");
		    }

		 
		clienteRepository.delete(obj);
	}

	public void baterPonto1(Integer id) {
	    Cliente cliente = findById(id);
	    cliente.setPonto(LocalDate.now());
	    clienteRepository.save(cliente);
	}
	public Cliente findByEmailAndSenha(String email, String senha) {
	    Optional<Cliente> clienteOptional = clienteRepository.findByEmail(email);
	    if (clienteOptional.isPresent()) {
	        Cliente cliente = clienteOptional.get();
	        if (encoder.matches(senha, cliente.getSenha())) {
	            return cliente;
	        }
	    }
	    throw new IllegalArgumentException("Credenciais inválidas, informe ao seu patrão"); // Lança exceção informando que as credenciais são inválidas
	}



}
