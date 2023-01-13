package com.attornatus.apiclient.tests;

import java.time.LocalDate;

import com.attornatus.apiclient.dto.PessoaDTO;
import com.attornatus.apiclient.entities.Endereco;
import com.attornatus.apiclient.entities.Pessoa;

public class Factory {

	public static Pessoa createPessoa() {
		Pessoa pessoa = new Pessoa(1L, "FULANO", LocalDate.parse("2022-07-22"));
		pessoa.getEnderecos().add(new Endereco(1L, "Rua do Fim", "60120-550", "Fortaleza", 1135,false,null));
		return pessoa;
	}

	public static PessoaDTO createPessoaDTO() {
		Pessoa pessoa = createPessoa();
		return new PessoaDTO(pessoa, pessoa.getEnderecos());
	}
}
