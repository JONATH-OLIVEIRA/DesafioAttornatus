package com.attornatus.apiclient.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.apiclient.dto.EnderecoDTO;
import com.attornatus.apiclient.entities.Endereco;
import com.attornatus.apiclient.repositories.EnderecoRepository;
import com.attornatus.apiclient.service.exceptions.DatabaseException;
import com.attornatus.apiclient.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Transactional(readOnly = true)
	public Page<EnderecoDTO> findAllPaged(Pageable pageRequest) {
		Page<Endereco> list = repository.findAll(pageRequest);

		return list.map(x -> new EnderecoDTO(x));
	}

	@Transactional(readOnly = true)
	public EnderecoDTO findaById(Long id) {
		Optional<Endereco> obj = repository.findById(id);
		Endereco entity = obj.orElseThrow(() -> new ResourceNotFoundException("Nao Encontrado"));
		return new EnderecoDTO(entity);
	}

	@Transactional(readOnly = true)
	public EnderecoDTO insert(EnderecoDTO dto) {
		Endereco entity = new Endereco();

		entity.setLogradouro(dto.getLogradouro());
		entity.setCep(dto.getCep());
		entity.setCidade(dto.getCidade());
		entity.setNumero(dto.getNumero());

		entity = repository.save(entity);
		return new EnderecoDTO(entity);
	}

	@Transactional
	public EnderecoDTO update(EnderecoDTO dto, Long id) {
		try {
			Endereco entity = repository.getById(id);

			entity.setLogradouro(dto.getLogradouro());
			entity.setCep(dto.getCep());
			entity.setCidade(dto.getCidade());
			entity.setNumero(dto.getNumero());

			entity = repository.save(entity);

			return new EnderecoDTO(entity);
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
