package br.com.sergio.apicliente.v1.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sergio.apicliente.v1.model.Cliente;
import br.com.sergio.apicliente.v1.repository.ClienteRepository;
import br.com.sergio.apicliente.v1.validator.ClienteValidator;

/**
 * Classe de serviços das Campanhas
 * 
 * @author Sérgio
 */
@Service
public class ClienteService {
	
	@Autowired
	private ClienteValidator clienteValidator;
	
	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Persiste os dados do clienste
	 * 
	 * @param email
	 * @param nome
	 * @param dataNascimento
	 * @param idTimeCoracao
	 * @return {@link Cliente}
	 */
	public Cliente persisteCliente(
			String email,
			String nome, 
			LocalDate dataNascimento,
			Integer idTimeCoracao) { 

		clienteValidator.verifica().seTimeDoCoracaoExiste(idTimeCoracao);
		
		Cliente cliente = clienteRepository.pesquisar(email);
		
		if (cliente != null) {
			clienteRepository.atualizar(email, nome, dataNascimento, idTimeCoracao);
			return clienteRepository.pesquisar(cliente.getId());
		} else {
			Integer idCliente = clienteRepository.criar(email, nome, dataNascimento, idTimeCoracao);
			return clienteRepository.pesquisar(idCliente);
		}
		
	}
	
}
