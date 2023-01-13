package com.attornatus.apiclient.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.attornatus.apiclient.dto.PessoaDTO;
import com.attornatus.apiclient.entities.Pessoa;
import com.attornatus.apiclient.repositories.PessoaRepository;
import com.attornatus.apiclient.service.exceptions.DatabaseException;
import com.attornatus.apiclient.service.exceptions.ResourceNotFoundException;
import com.attornatus.apiclient.tests.Factory;

@ExtendWith(SpringExtension.class)
public class PessoaServiceTests {

	@InjectMocks
	private PessoaService service;

	@Mock
	private PessoaRepository repository;

	private long exintingId;
	private long nonExistingId;
	private long dependentId;
	private PageImpl<Pessoa> page;
	private Pessoa pessoa;

	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		pessoa = Factory.createPessoa();
		page = new PageImpl<>(List.of(pessoa));

		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(pessoa);

		Mockito.when(repository.findById(exintingId)).thenReturn(Optional.of(pessoa));

		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

		Mockito.doNothing().when(repository).deleteById(exintingId);

		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);

		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
	}

	@Test
	public void findAllPagedShouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 10);

		Page<PessoaDTO> result = service.findAllPaged(pageable);

		Assertions.assertNotNull(result);
		Mockito.verify(repository).findAll(pageable);
	}

	@Test
	public void deleteShouldDatabaseExceptionWhenDependentId() {

		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});
		Mockito.verify(repository).deleteById(dependentId);
	}

	@Test
	public void deleteShouldResourceNotFoundExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
		Mockito.verify(repository).deleteById(nonExistingId);
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {

		Assertions.assertDoesNotThrow(() -> {
			service.delete(exintingId);
		});
		Mockito.verify(repository).deleteById(exintingId);
	}

}
