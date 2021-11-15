package org.serratec.backend.TrabalhoFinal.repository;

import org.serratec.backend.TrabalhoFinal.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long> {
	
	Endereco findByCep (String cep);
}
