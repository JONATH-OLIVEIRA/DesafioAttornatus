package com.attornatus.apiclient.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.attornatus.apiclient.dto.PessoaDTO;
import com.attornatus.apiclient.resource.PessoaResource;
import com.attornatus.apiclient.service.exceptions.ResourceNotFoundException;
import com.attornatus.apiclient.services.PessoaService;
import com.attornatus.apiclient.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PessoaResource.class)
public class PessoaResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PessoaService service;
		
	@Autowired
	private ObjectMapper objectMapper;
	
	private Long existiId;
	private Long nonExistiId;
	
	private PessoaDTO pessoaDTO;
	private PageImpl<PessoaDTO> page;

	@BeforeEach
	void setUp() throws Exception {

		existiId = 1L;
		nonExistiId = 2L;

		pessoaDTO = Factory.createPessoaDTO();
		page = new PageImpl<>(List.of(pessoaDTO));

		when(service.findAllPaged(any())).thenReturn(page);

		when(service.findaById(existiId)).thenReturn(pessoaDTO);
		when(service.findaById(nonExistiId)).thenThrow(ResourceNotFoundException.class);
		
		when(service.update(eq(existiId), any())).thenReturn(pessoaDTO);
		when(service.update(eq(nonExistiId), any())).thenThrow(ResourceNotFoundException.class);

	}
	
	@Test
	public void updateShouldReturnPessoaDTOWhenIdExists()throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(pessoaDTO);
		
		ResultActions result = mockMvc.perform(put("/pessoa/{id}", nonExistiId) 
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.nome").exists());
		
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExists()throws Exception {
		
	}

	@Test
	public void findAllShouldReturnPage() throws Exception {
		ResultActions result = mockMvc.perform(get("/pessoa").accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
	}

	@Test
	public void findByIdShouldReturnPessoaWhenIdExists() throws Exception {

		ResultActions result = mockMvc.perform(get("/pessoa/{id}", existiId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.nome").exists());
	}

	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

		ResultActions result = mockMvc.perform(get("/pessoa/{id}", nonExistiId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}

}
