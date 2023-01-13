package com.attornatus.apiclient.dto;

import java.io.Serializable;

import com.attornatus.apiclient.entities.Endereco;

public class EnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String logradouro;
	private String cep;
	private String cidade;
	private Integer numero;
	private boolean end_principal;
	private String id_pessoa;

	public EnderecoDTO() {
	}

	public EnderecoDTO(Long id, String logradouro, String cep, String cidade, Integer numero, boolean end_principal,
			String id_pessoa) {
		this.id = id;
		this.logradouro = logradouro;
		this.cep = cep;
		this.cidade = cidade;
		this.numero = numero;
		this.end_principal = end_principal;
		this.id_pessoa = id_pessoa;
	}

	public EnderecoDTO(Endereco entity) {
		this.id = entity.getId();
		this.logradouro = entity.getLogradouro();
		this.cep = entity.getCep();
		this.cidade = entity.getCidade();
		this.numero = entity.getNumero();
		this.end_principal = entity.isEnd_principal();
		this.id_pessoa = entity.getId_pessoa();

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

	public Long getId() {
		return id;
	}

	public boolean isEnd_principal() {
		return end_principal;
	}

	public void setEnd_principal(boolean end_principal) {
		this.end_principal = end_principal;
	}

	public String getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(String id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

}
