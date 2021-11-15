package org.serratec.backend.TrabalhoFinal.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.serratec.backend.TrabalhoFinal.domain.Produto;
import org.serratec.backend.TrabalhoFinal.dto.ProdutoRequestDTO;
import org.serratec.backend.TrabalhoFinal.dto.ProdutoResponseDTO;
import org.serratec.backend.TrabalhoFinal.service.ProdutoService;
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
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	@ApiOperation(value = "Lista todos os produtos", notes = "Listagem de produtos")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Produtos listados com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 422, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<List<ProdutoResponseDTO>> listar() {
		return ResponseEntity.ok(produtoService.findAll());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Consultar produto por id", notes = "Consultar produto")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Produto retornado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu alguma excessão") })
	public ResponseEntity<ProdutoResponseDTO> pesquisar(@Valid @PathVariable Long id) {
		if (produtoService.idExiste(id)) {
			return ResponseEntity.ok(new ProdutoResponseDTO(produtoService.findById(id)));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Inserir produto", notes = "Inserir produto")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Produto inserido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu alguma excessão") })
	public ResponseEntity<ProdutoResponseDTO> inserir(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
		Produto produto = produtoService.insert(produtoRequestDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getIdProduto())
				.toUri();
		return ResponseEntity.created(uri).body(new ProdutoResponseDTO(produto));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar produto por id", notes = "Atualizar produto por id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Produto atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu alguma excessão") })
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id,
			@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
		if (produtoService.idExiste(id)) {
			return ResponseEntity.ok(new ProdutoResponseDTO(produtoService.update(produtoRequestDTO, id)));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar produto por id", notes = "Deletar produto")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Produto removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu alguma excessão") })
	public ResponseEntity<String> remover(@Valid @PathVariable Long id) {
		if (!produtoService.idExiste(id)) {
			return ResponseEntity.notFound().build();
		}
		produtoService.remover(id);
		return ResponseEntity.ok("Produto removido");
	}
}