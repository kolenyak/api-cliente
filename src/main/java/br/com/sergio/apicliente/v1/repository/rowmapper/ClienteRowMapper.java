package br.com.sergio.apicliente.v1.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.sergio.apicliente.v1.model.Cliente;

/**
 * Responsável por mapear o registro na modelo {@link Cliente}
 * 
 * @author Sérgio
 */
public class ClienteRowMapper implements RowMapper<Cliente> {
	
	@Override
	public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Cliente()
				.id(rs.getInt("id"))
				.email(rs.getString("email"))
				.nome(rs.getString("nome"))
				.idTimeCoracao(rs.getInt("id_time_coracao"))
				.dataNascimento(rs.getDate("data_nascimento").toLocalDate());
	}
	
}