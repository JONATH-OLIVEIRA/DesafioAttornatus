package com.attornatus.apiclient.resource;

import java.net.URI;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.attornatus.apiclient.dto.EnderecoDTO;
import com.attornatus.apiclient.services.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoService service;

	// faz a listagem dos enderecos
	@GetMapping()
	public ResponseEntity<Page<EnderecoDTO>> findAll(Pageable pageable) {

		Page<EnderecoDTO> list = service.findAllPaged(pageable);

		return ResponseEntity.ok().body(list);
	}
	
	// faz as listagem dos enderecos da pessoa passando o id da pessoa por requisicao
	@RequestMapping(value = "/listarEnderecoPessoa", method = RequestMethod.GET)	
	public List<EnderecoDTO> findByAdrress(@RequestParam(name = "id", required = false) String id_pessoa) {

		List<EnderecoDTO> list = service.findByAdrress(id_pessoa);
		
		return list;
	}

	// faz a insercao dos enderecos
	@PostMapping
	public ResponseEntity<EnderecoDTO> insert(@RequestBody EnderecoDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	// faz a buscar por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> findaById(@PathVariable Long id) {
		EnderecoDTO dto = service.findaById(id);
		return ResponseEntity.ok().body(dto);
	}

	// atualiza o endereco
	@PutMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> update(@RequestBody EnderecoDTO dto, @PathVariable Long id) {
		dto = service.update(dto, id);
		return ResponseEntity.ok().body(dto);

	}

	// faz o delete do endereco por id
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

}
