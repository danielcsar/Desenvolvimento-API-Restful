package org.serratec.backend.TrabalhoFinal.service;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.TrabalhoFinal.domain.Cliente;
import org.serratec.backend.TrabalhoFinal.dto.ClienteRequestDTO;
import org.serratec.backend.TrabalhoFinal.dto.ClienteResponseDTO;


public interface ClienteService {

	List<Cliente> pesquisarTodos();
	List<ClienteResponseDTO> findAll();
	Optional<Cliente> pesquisarUm(Long idCliente);
	Cliente findbyID(Long idCliente);
	Cliente inserir(Cliente cliente);
	Cliente insert(ClienteRequestDTO cliente);
	Cliente atualizarCliente(ClienteRequestDTO clienteRequestDTO, Long id);
	boolean idExiste(Long id);
	void remover(Long id);
}
