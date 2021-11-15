package org.serratec.backend.TrabalhoFinal.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.serratec.backend.TrabalhoFinal.domain.Cliente;
import org.serratec.backend.TrabalhoFinal.dto.ClienteRequestDTO;
import org.serratec.backend.TrabalhoFinal.dto.ClienteResponseDTO;
import org.serratec.backend.TrabalhoFinal.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	@ApiOperation(value = "Consultar todos os clientes", notes = "Consultar clientes")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Clientes listados com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 422, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<List<ClienteResponseDTO>> findAll() {
		return ResponseEntity.ok(clienteService.findAll());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Consultar cliente por id", notes = "Consultar cliente id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente listado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 422, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<ClienteResponseDTO> buscar(@Valid @PathVariable Long id) {
		if (clienteService.idExiste(id)) {
			return ResponseEntity.ok(new ClienteResponseDTO(clienteService.findbyID(id)));
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Inserir um cliente", notes = "Inserir cliente")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente inserido com sucesso"),
			@ApiResponse(code = 201, message = "Cliente criado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 422, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<ClienteResponseDTO> inserir(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
		Cliente cliente = clienteService.insert(clienteRequestDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getIdCliente()).toUri();
		return ResponseEntity.created(uri).body(new ClienteResponseDTO(cliente));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar cliente por id", notes = "Atualizar cliente por id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente atualizado com sucesso"),
			@ApiResponse(code = 201, message = "Cliente atualizado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 422, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
		if (clienteService.idExiste(id)) {
			return ResponseEntity.ok(new ClienteResponseDTO(clienteService.atualizarCliente(clienteRequestDTO, id)));		
		}		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar cliente por id", notes = "Deletar cliente")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente deletado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 422, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<String> remover(@Valid @PathVariable Long id) {
		if (!clienteService.idExiste(id)) {
			return ResponseEntity.notFound().build();
		}
		clienteService.remover(id);
		return  ResponseEntity.ok("Cliente removido");
	}
}