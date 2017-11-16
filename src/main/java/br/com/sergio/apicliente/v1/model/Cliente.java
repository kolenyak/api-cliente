package br.com.sergio.apicliente.v1.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Modelo dos dados de Cliente
 * 
 * @author Sérgio
 */
public class Cliente   {

	private Integer id = null;
	
	@JsonProperty("nome")
	private String nome = null;

	@JsonProperty("email")
	private String email = null;

	@JsonProperty("dataNascimento")
	private LocalDate dataNascimento = null;

	@JsonProperty("idTimeCoracao")
	private Integer idTimeCoracao = null;
	
	public Cliente id(Integer id) {
		this.id = id;
		return this;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente nome(String nome) {
		this.nome = nome;
		return this;
	}

	/**
	 * Get nome
	 * @return nome
	 **/
	@ApiModelProperty(example = "Fulano de Tal", required = true, value = "")
	@NotNull @NotBlank
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Get email
	 * @return email
	 **/
	@ApiModelProperty(example = "fulano@email.com", required = true, value = "")
	@NotNull @NotBlank
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cliente dataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}

	/**
	 * Get dataNascimento
	 * @return dataNascimento
	 **/
	@ApiModelProperty(example = "2017-11-13", required = true, value = "")
	@NotNull
	@Valid
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Cliente idTimeCoracao(Integer idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
		return this;
	}

	/**
	 * Get idTimeCoracao
	 * @return idTimeCoracao
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull
	public Integer getIdTimeCoracao() {
		return idTimeCoracao;
	}

	public void setIdTimeCoracao(Integer idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Cliente cliente = (Cliente) o;
		return Objects.equals(this.nome, cliente.nome) &&
				Objects.equals(this.email, cliente.email) &&
				Objects.equals(this.dataNascimento, cliente.dataNascimento) &&
				Objects.equals(this.idTimeCoracao, cliente.idTimeCoracao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, email, dataNascimento, idTimeCoracao);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cliente {\n");

		sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    dataNascimento: ").append(toIndentedString(dataNascimento)).append("\n");
		sb.append("    idTimeCoracao: ").append(toIndentedString(idTimeCoracao)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}

