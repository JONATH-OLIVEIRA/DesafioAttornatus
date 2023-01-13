package com.attornatus.apiclient.services;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.apiclient.dto.PessoaDTO;
import com.attornatus.apiclient.repositories.PessoaRepository;
import com.attornatus.apiclient.service.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class PessoaServiceIT {

	@Autowired
	private PessoaService service;

	@Autowired
	private PessoaRepository repository;

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalPessoa;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalPessoa = 3L;
	}

	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {

		service.delete(existingId);

		Assertions.assertEquals(countTotalPessoa - 1, repository.count());
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}

	@Test
	public void findAllPagedShouldReturnPageWhenPage0Size1() {

		PageRequest pageRequest = PageRequest.of(0, 1);

		Page<PessoaDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(0, result.getNumber());
		Assertions.assertEquals(1, result.getSize());
		Assertions.assertEquals(countTotalPessoa, result.getTotalElements());
	}

	@Test
	public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist() {

		PageRequest pageRequest = PageRequest.of(50, 1);

		Page<PessoaDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	public void findAllPagedShouldReturnSortedPageWhenSortByName() {

		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("nome"));

		Page<PessoaDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals("ALOSON", result.getContent().get(0).getNome());
		Assertions.assertEquals("FULANO", result.getContent().get(1).getNome());
		Assertions.assertEquals("OTOCHE", result.getContent().get(2).getNome());
	}
}
