package com.attornatus.apiclient.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = ("tb_pessoa"))
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate data_nascimento;

	public Pessoa() {
	}

	public Pessoa(Long id, String nome, LocalDate data_nascimento) {
		this.id = id;
		this.nome = nome;
		this.data_nascimento = data_nascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	

	@Override
	public int hashCode() {
		return Objects.hash(data_nascimento, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(data_nascimento, other.data_nascimento) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}

}
