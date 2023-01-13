package com.attornatus.apiclient.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.apiclient.dto.PessoaDTO;
import com.attornatus.apiclient.entities.Pessoa;
import com.attornatus.apiclient.repositories.PessoaRepository;
import com.attornatus.apiclient.service.exceptions.DatabaseException;
import com.attornatus.apiclient.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	@Transactional(readOnly = true)
	public Page<PessoaDTO> findAllPaged(Pageable pageRequest) {
		Page<Pessoa> list = repository.findAll(pageRequest);

		return list.map(x -> new PessoaDTO(x));
	}

	@Transactional(readOnly = true)
	public PessoaDTO findaById(Long id) {
		Optional<Pessoa> obj = repository.findById(id);
		Pessoa entity = obj.orElseThrow(() -> new ResourceNotFoundException("Nao Encontrado"));
		return new PessoaDTO(entity, entity.getEnderecos());
	}

	@Transactional(readOnly = true)
	public PessoaDTO insert(PessoaDTO dto) {
		Pessoa entity = new Pessoa();
		entity.setNome(dto.getNome());
		entity.setData_nascimento(dto.getData_nascimento());
		entity = repository.save(entity);
		return new PessoaDTO(entity);
	}

	@Transactional
	public PessoaDTO update(PessoaDTO dto, Long id) {
		try {
			Pessoa entity = repository.getReferenceById(id);
			entity.setNome(dto.getNome());
			entity.setData_nascimento(dto.getData_nascimento());
			entity = repository.save(entity);

			return new PessoaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id nao encontrado" + " " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id nao encontrado" + " " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violacao de Integridade");
		}

	}

}
