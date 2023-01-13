package com.attornatus.apiclient.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.attornatus.apiclient.dto.PessoaDTO;
import com.attornatus.apiclient.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaService service;

	// faz a listagem das pessoas paginada
	@GetMapping()
	public ResponseEntity<Page<PessoaDTO>> findAll(Pageable pageable) {

		Page<PessoaDTO> list = service.findAllPaged(pageable);

		return ResponseEntity.ok().body(list);
	}

	// faz a insercao das pessoas
	@PostMapping
	public ResponseEntity<PessoaDTO> insert(@RequestBody PessoaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	// faz a buscar por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaDTO> findaById(@PathVariable Long id) {
		PessoaDTO dto = service.findaById(id);
		return ResponseEntity.ok().body(dto);
	}

	// atualiza a pessoa
	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaDTO> update(@RequestBody PessoaDTO dto, @PathVariable Long id) {
		dto = service.update(dto, id);
		return ResponseEntity.ok().body(dto);

	}

	// faz o delete da pessoa por id
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

}
