package com.attornatus.apiclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.attornatus.apiclient.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
