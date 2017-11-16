package br.com.sergio.apicliente.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sergio.apicliente.v1.model.Cliente;
import br.com.sergio.apicliente.v1.service.ClienteService;

@RestController
public class ClientesApiController implements ClientesApi {
	
	@Autowired
	private ClienteService clienteService;

    public Cliente cadastraCliente(@Valid @RequestBody Cliente dados) {
    	
    	return clienteService.persisteCliente(
    			dados.getEmail(),
    			dados.getNome(),
    			dados.getDataNascimento(),
    			dados.getIdTimeCoracao());
    }

}
