package br.com.sergio.apicliente.v1.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sergio.apicliente.v1.model.ApiRespostaErro;
import br.com.sergio.apicliente.v1.model.Cliente;

/**
 * Teste de integração para o endpoint de /clientes
 *
 * @author Sérgio
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class ClientesApiControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void deveInserirUmNovoCliente() {
		
		Cliente clienteEnvio = new Cliente()
				.nome("Cliente Teste")
				.email("cliente-teste@email.com")
				.idTimeCoracao(4)
				.dataNascimento(LocalDate.of(1990, 11, 13));
		
		ResponseEntity<Cliente> responseEntity = restTemplate.postForEntity("/clientes", clienteEnvio, Cliente.class);
		Cliente clienteResposta = responseEntity.getBody();
		
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.CREATED));
		assertNotNull(clienteResposta);
		assertNotNull(clienteResposta.getId());
		assertThat(clienteResposta.getNome(), equalTo("Cliente Teste"));
		assertThat(clienteResposta.getEmail(), equalTo("cliente-teste@email.com"));
		assertThat(clienteResposta.getDataNascimento(), equalTo(LocalDate.of(1990, 11, 13)));
		assertThat(clienteResposta.getIdTimeCoracao(), equalTo(4));
	}
	
	@Test
	public void deveAtualizaOsDadosCliente() {
		
		Integer idDoClienteEsperado = 1;
		Cliente clienteEnvio = new Cliente()
				.nome("João Palmeirense Campeão")
				.email("joao@email.com")
				.idTimeCoracao(4)
				.dataNascimento(LocalDate.of(1990, 01, 01));
		
		ResponseEntity<Cliente> responseEntity = restTemplate.postForEntity("/clientes", clienteEnvio, Cliente.class);
		Cliente clienteResposta = responseEntity.getBody();
		
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.CREATED));
		assertNotNull(clienteResposta);
		assertThat(clienteResposta.getId(), equalTo(idDoClienteEsperado));
		assertThat(clienteResposta.getNome(), equalTo("João Palmeirense Campeão"));
		assertThat(clienteResposta.getEmail(), equalTo("joao@email.com"));
		assertThat(clienteResposta.getDataNascimento(), equalTo(LocalDate.of(1990, 01, 01)));
		assertThat(clienteResposta.getIdTimeCoracao(), equalTo(4));
	}
	
	@Test
	public void deveRetornarStatusBadRequestQuandoIdTimeCoracaoNaoExistir() {
		
		Cliente clienteEnvio = new Cliente()
				.nome("João Palmeirense Campeão")
				.email("joao@email.com")
				.idTimeCoracao(8)
				.dataNascimento(LocalDate.of(1990, 01, 01));
		
		ResponseEntity<ApiRespostaErro> responseEntity = restTemplate.postForEntity("/clientes", clienteEnvio, ApiRespostaErro.class);
		ApiRespostaErro apiRespostaErro = responseEntity.getBody();
		
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
		assertNotNull(apiRespostaErro);
		assertThat(apiRespostaErro.getParametro(), equalTo("idTimeCoracao"));
		assertThat(apiRespostaErro.getMensagem(), equalTo("Esse time não foi encontrado"));
	}

}
