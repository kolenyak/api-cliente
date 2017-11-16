package br.com.sergio.apicliente.v1.repository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.sergio.apicliente.v1.model.Cliente;
import br.com.sergio.apicliente.v1.repository.rowmapper.ClienteRowMapper;

/**
 * Repositório para dados do Cliente com conexão jdbc
 * 
 * @author Sérgio
 */
@Repository
public class ClienteRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Value("${cliente.cmd.update}")
	private String comandoSqlUpdate;

	@Value("${cliente.cmd.insert}")
	private String comandoSqlInsert;

	@Value("${cliente.cmd.select.where.email}")
	private String comandoSqlSelectWhereEmail;

	@Value("${cliente.cmd.select.where.id}")
	private String comandoSqlSelectWhereId;

	/**
	 * @param email
	 * @param nomeCliente
	 * @param dataNascimento
	 * @param idTimeCoracao
	 */
	public void atualizar(String email, String nomeCliente, LocalDate dataNascimento, Integer idTimeCoracao) {
		SqlParameterSource parametros = new MapSqlParameterSource()
				.addValue("email", email)
				.addValue("nomeCliente", nomeCliente)
				.addValue("dataNascimento", dataNascimento)
				.addValue("idTimeCoracao", idTimeCoracao);

		jdbcTemplate.update(comandoSqlUpdate, parametros);
	}

	/**
	 * @param email
	 * @param nomeCliente
	 * @param dataNascimento
	 * @param idTimeCoracao
	 * @return
	 */
	public Integer criar(String email, String nomeCliente, LocalDate dataNascimento, Integer idTimeCoracao) {
		SqlParameterSource parametros = new MapSqlParameterSource()
				.addValue("email", email)
				.addValue("nomeCliente", nomeCliente)
				.addValue("dataNascimento", dataNascimento)
				.addValue("idTimeCoracao", idTimeCoracao);
			
			KeyHolder chaveiro = new GeneratedKeyHolder();
			jdbcTemplate.update(comandoSqlInsert, parametros, chaveiro);
			try {
				return chaveiro.getKey().intValue();
			} catch (DataRetrievalFailureException e){
				return null;
			}

	}

	/**
	 * @param idCliente
	 * @return {@link Cliente}
	 */
	public Cliente pesquisar(Integer idCliente) {

		SqlParameterSource parametros = new MapSqlParameterSource()
				.addValue("idCliente", idCliente);

		try {
			return jdbcTemplate.queryForObject(comandoSqlSelectWhereId, parametros, new ClienteRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return  null;
		}
	}
	
	/**
	 * @param email
	 * @return {@link Cliente}
	 */
	public Cliente pesquisar(String email) {

		SqlParameterSource parametros = new MapSqlParameterSource()
				.addValue("email", email);

		try {
			return jdbcTemplate.queryForObject(comandoSqlSelectWhereEmail, parametros, new ClienteRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return  null;
		}
	}

}
