package com.attornatus.apiclient.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = ("tb_endereco"))
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String logradouro;
	private String cep;
	private String cidade;
	private Integer numero;

	public Endereco() {
	}

	public Endereco(Long id, String logradouro, String cep, String cidade, Integer numero) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.cep = cep;
		this.cidade = cidade;
		this.numero = numero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, cidade, id, logradouro, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(cidade, other.cidade) && Objects.equals(id, other.id)
				&& Objects.equals(logradouro, other.logradouro) && Objects.equals(numero, other.numero);
	}

}
