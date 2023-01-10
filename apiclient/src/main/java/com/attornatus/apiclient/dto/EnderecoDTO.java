package com.attornatus.apiclient.dto;

import java.io.Serializable;

import com.attornatus.apiclient.entities.Endereco;

public class EnderecoDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String logradouro;
	private String cep;
	private String cidade;
	private Integer numero;
	
	public EnderecoDTO() {		
	}

	public EnderecoDTO(Long id, String logradouro, String cep, String cidade, Integer numero) {
		this.id = id;
		this.logradouro = logradouro;
		this.cep = cep;
		this.cidade = cidade;
		this.numero = numero;
	}
	
	public EnderecoDTO(Endereco entity) {
		this.id = entity.getId();
		this.logradouro = entity.getLogradouro();
		this.cep = entity.getCep();
		this.cidade = entity.getCidade();
		this.numero = entity.getNumero();
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
	
}
