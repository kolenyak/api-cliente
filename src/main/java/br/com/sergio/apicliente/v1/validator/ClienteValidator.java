package br.com.sergio.apicliente.v1.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sergio.apicliente.v1.infra.exception.ApiRespostaException;
import br.com.sergio.apicliente.v1.repository.TimeRepository;

/**
 * Responsável por verificar os dados da entidade
 * 
 * @author Sérgio
 */
@Component
public class ClienteValidator {
	
	@Autowired
	private TimeRepository timeRepository;
	
	public Verifica verifica() {
		return new Verifica();
	}
	
	
	public class Verifica {
		
		/**
		 * Verifica se o time existe
		 * 
		 * @param idTimeCoracao
		 * @return {@link Verifica}
		 */
		public Verifica seTimeDoCoracaoExiste(Integer idTimeCoracao) {
			
			if(!timeRepository.exists(idTimeCoracao)) {
				throw new ApiRespostaException("idTimeCoracao", "Esse time não foi encontrado");
			}
			
			return this;
		}
		
	}
	
	

}
