package org.serratec.backend.TrabalhoFinal.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.serratec.backend.TrabalhoFinal.dto.PedidoRequestDTO;
import org.serratec.backend.TrabalhoFinal.dto.PedidoResponseDTO;
import org.serratec.backend.TrabalhoFinal.service.PedidoService;
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
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	@ApiOperation(value = "Lista todos os pedidos", notes = "Listagem de pedidos")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Pedidos listados com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 422, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<List<PedidoResponseDTO>> listar() {
		return ResponseEntity.ok(pedidoService.findAll());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Consultar pedido por id", notes = "Consultar pedido")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Busca realizada com sucesso"),
			@ApiResponse(code = 201, message = "Pedido localizado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<PedidoResponseDTO> pequisar(@Valid @PathVariable Long id) {
		if (pedidoService.idExiste(id)) {
			return ResponseEntity.ok(pedidoService.pesquisarUm(id));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Inserir um pedido", notes = "Inserir pedido")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Pedido inserido com sucesso"),
			@ApiResponse(code = 201, message = "Pedido criado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 422, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<PedidoResponseDTO> inserir(@RequestBody PedidoRequestDTO pedidoRequestDTO) {
		PedidoResponseDTO pedido = pedidoService.insert(pedidoRequestDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getIdPedido())
				.toUri();
		return ResponseEntity.created(uri).body(pedido);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar pedido pelo id", notes = "Atualizar pedido pelo id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Pedido atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu alguma excessão")})
	public ResponseEntity<PedidoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {
		if (pedidoService.idExiste(id)) {
			return ResponseEntity.ok(new PedidoResponseDTO(pedidoService.update(pedidoRequestDTO, id)));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar pedido por id", notes = "Deletar pedido")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Produto removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu alguma excessão") })
	public ResponseEntity<String> remover(@Valid @PathVariable Long id) {
		if (!pedidoService.idExiste(id)) {
			return ResponseEntity.notFound().build();
		}
		pedidoService.remover(id);
		return ResponseEntity.ok("Pedido removido");
	}
}