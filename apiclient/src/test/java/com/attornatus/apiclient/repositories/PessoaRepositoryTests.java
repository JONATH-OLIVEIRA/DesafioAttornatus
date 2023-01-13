package com.attornatus.apiclient.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.attornatus.apiclient.entities.Pessoa;
import com.attornatus.apiclient.tests.Factory;

@DataJpaTest
public class PessoaRepositoryTests {

	@Autowired
	private PessoaRepository repository;

	private long exintingId;

	private long nonExistingId;
	
	private long countTotalPessoas;

	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		nonExistingId = 20L;
		countTotalPessoas = 3;
	}
	
	@Test 
	public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
		Pessoa pessoa = Factory.createPessoa();
		pessoa.setId(null);
		
		pessoa = repository.save(pessoa);
		
		Assertions.assertNotNull(pessoa.getId());
		Assertions.assertEquals(countTotalPessoas + 1, pessoa.getId());
		
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {

		repository.deleteById(exintingId);

		Optional<Pessoa> result = repository.findById(exintingId);

		Assertions.assertFalse(result.isPresent());

	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesExist() {

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});

	}

}
