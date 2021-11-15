package org.serratec.backend.TrabalhoFinal.repository;

import org.serratec.backend.TrabalhoFinal.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Cliente findByEmailCliente(String email);
	Cliente findByCpfCliente(String cpf);

}

