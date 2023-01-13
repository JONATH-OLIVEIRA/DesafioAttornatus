package com.attornatus.apiclient.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.attornatus.apiclient.service.exceptions.DatabaseException;
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
	
	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	
	private PessoaDTO pessoaDTO;
	private PageImpl<PessoaDTO> page;

	@BeforeEach
	void setUp() throws Exception {

		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;

		pessoaDTO = Factory.createPessoaDTO();
		page = new PageImpl<>(List.of(pessoaDTO));

		when(service.findAllPaged(any())).thenReturn(page);

		when(service.findById(existingId)).thenReturn(pessoaDTO);
		when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

		when(service.insert(any())).thenReturn(pessoaDTO);
	
		when(service.update(eq(existingId), any())).thenReturn(pessoaDTO);
		when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);

		doNothing().when(service).delete(existingId);
		doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
		doThrow(DatabaseException.class).when(service).delete(dependentId);
	}
	
	@Test
	public void insertShouldReturnPessoaDTOCreated() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(pessoaDTO);

		ResultActions result =
				mockMvc.perform(post("/pessoa")
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.nome").exists());
		result.andExpect(jsonPath("$.data_nascimento").exists());
	}

	@Test
	public void updateShouldReturnPessoaDTOWhenIdExists() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(pessoaDTO);

		ResultActions result =
				mockMvc.perform(put("/pessoa/{id}", existingId)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.nome").exists());
		result.andExpect(jsonPath("$.data_nascimento").exists());
	}

	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(pessoaDTO);

		ResultActions result =
				mockMvc.perform(put("/pessoa/{id}", nonExistingId)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}

	@Test
	public void findAllShouldReturnPage() throws Exception {

		ResultActions result =
				mockMvc.perform(get("/pessoa")
					.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
	}

	@Test
	public void findByIdShouldReturnPessoaWhenIdExists() throws Exception {

		ResultActions result =
				mockMvc.perform(get("/pessoa/{id}", existingId)
					.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.nome").exists());
		result.andExpect(jsonPath("$.data_nascimento").exists());
	}

	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		ResultActions result =
				mockMvc.perform(get("/pessoa/{id}", nonExistingId)
					.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}

}
