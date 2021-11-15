package org.serratec.backend.TrabalhoFinal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.TrabalhoFinal.config.EmailConfig;
import org.serratec.backend.TrabalhoFinal.domain.Cliente;
import org.serratec.backend.TrabalhoFinal.dto.ClienteResponseDTO;
import org.serratec.backend.TrabalhoFinal.exception.CpfException;
import org.serratec.backend.TrabalhoFinal.exception.EmailException;
import org.serratec.backend.TrabalhoFinal.dto.ClienteRequestDTO;
import org.serratec.backend.TrabalhoFinal.repository.ClienteRepository;
import org.serratec.backend.TrabalhoFinal.service.ClienteService;
import org.serratec.backend.TrabalhoFinal.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
    private EmailConfig email;

	@Override
	public List<Cliente> pesquisarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public List<ClienteResponseDTO> findAll() {		
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteResponseDTO> clientesDTO = new ArrayList<ClienteResponseDTO>();	
		
		for (Cliente cliente : clientes) {			
			ClienteResponseDTO clienteDTO = new ClienteResponseDTO(cliente);
			clientesDTO.add(clienteDTO);			
		}
		return clientesDTO;
	}

	@Override
	public Optional<Cliente> pesquisarUm(Long idCliente) {
		return clienteRepository.findById(idCliente);
	}

	@Override
	public Cliente findbyID(Long idCliente) {
		Cliente cliente = new Cliente();
		
		if(clienteRepository.findById(idCliente).isPresent()) {
			cliente.setIdCliente(clienteRepository.findById(idCliente).get().getIdCliente());
			cliente.setNomeCliente(clienteRepository.findById(idCliente).get().getNomeCliente());
			cliente.setEmailCliente(clienteRepository.findById(idCliente).get().getEmailCliente());
			cliente.setEndereco(clienteRepository.findById(idCliente).get().getEndereco());
			return cliente;
		}		
		return null;
	}

	@Override
	public Cliente inserir(Cliente cliente) throws EmailException, CpfException{
		if(clienteRepository.findByEmailCliente(cliente.getEmailCliente()) != null) {
			throw new EmailException("Email já cadastrado.");
		}		
		if(clienteRepository.findByCpfCliente(cliente.getCpfCliente()) != null) {
			throw new CpfException("CPF já existente no banco de dados.");
		}
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente insert(ClienteRequestDTO clienteRequestDTO) throws EmailException, CpfException{
		
		Cliente clienteTeste = clienteRepository.findByEmailCliente(clienteRequestDTO.getEmailCliente());
		
		if(clienteTeste != null) {			
			throw new EmailException("Email já cadastrado.");
		}	
		
		clienteTeste = clienteRepository.findByCpfCliente(clienteRequestDTO.getCpfCliente());
		
		if(clienteTeste != null) {			
			throw new CpfException("CPF já existente no banco de dados.");
		}
		
		Cliente cliente = new Cliente();
		cliente.setNomeCliente(clienteRequestDTO.getNomeCliente());
		cliente.setEmailCliente(clienteRequestDTO.getEmailCliente());
		cliente.setCpfCliente(clienteRequestDTO.getCpfCliente());
		cliente.setNascimentoCliente(clienteRequestDTO.getNascimentoCliente());
		cliente.setEndereco(enderecoService.buscarCep(clienteRequestDTO.getCep()));
		cliente = clienteRepository.save(cliente);
		
		email.sendEmail(cliente.getEmailCliente(), "Cadastro de Usuário" , cliente.toString());
			
		return cliente;
	}
	
	@Override
	public Cliente atualizarCliente(ClienteRequestDTO clienteRequestDTO, Long id) {
		Cliente cliente = clienteRepository.getById(id);
		
		cliente.setNomeCliente(clienteRequestDTO.getNomeCliente());
		cliente.setEmailCliente(clienteRequestDTO.getEmailCliente());
		cliente.setCpfCliente(clienteRequestDTO.getCpfCliente());
		cliente.setNascimentoCliente(clienteRequestDTO.getNascimentoCliente());
		cliente.setEndereco(enderecoService.buscarCep(clienteRequestDTO.getCep()));
		
		clienteRepository.save(cliente);
		
		email.sendEmail(cliente.getEmailCliente(), "Atualização de Usuário" , cliente.toString());
		
		return cliente;
	}

	@Override
	public boolean idExiste(Long id) {
		return clienteRepository.existsById(id);
	}

	@Override
	public void remover(Long id) {
		clienteRepository.deleteById(id);
	}	
}
