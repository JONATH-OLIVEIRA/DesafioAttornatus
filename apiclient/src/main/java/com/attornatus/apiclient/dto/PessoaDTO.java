package com.attornatus.apiclient.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.attornatus.apiclient.entities.Pessoa;

public class PessoaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	private LocalDate data_nascimento;

	public PessoaDTO() {
	}

	public PessoaDTO(long id, String nome, LocalDate data_nascimento) {
		this.id = id;
		this.nome = nome;
		this.data_nascimento = data_nascimento;
	}

	public PessoaDTO(Pessoa entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.data_nascimento = entity.getData_nascimento();
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

}
