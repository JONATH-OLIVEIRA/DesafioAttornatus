package com.attornatus.apiclient.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.attornatus.apiclient.dto.EnderecoDTO;
import com.attornatus.apiclient.entities.Endereco;





@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	//@Query(value = "select u from Endereco u where upper(trim(u.id_pessoa)) like %?1% ")
	//List<EnderecoDTO> findByAdrressId (String id_pessoa);
	
	@Query(value = "select u from Endereco u where upper(trim(u.id_pessoa)) like %?1% ")
	List<EnderecoDTO> findByAdrressId (String id_pessoa );

}
