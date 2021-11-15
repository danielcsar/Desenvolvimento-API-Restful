package org.serratec.backend.TrabalhoFinal.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.serratec.backend.TrabalhoFinal.domain.Categoria;
import org.serratec.backend.TrabalhoFinal.dto.CategoriaRequestDTO;
import org.serratec.backend.TrabalhoFinal.dto.CategoriaResponseDTO;
import org.serratec.backend.TrabalhoFinal.service.CategoriaService;
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
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	@ApiOperation(value = "Consultar todas as categorias", notes = "Consultar categorias")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todas as Categorias"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")})
	public ResponseEntity<List<CategoriaResponseDTO>> buscarTodos() {
		return ResponseEntity.ok(categoriaService.findAll());

	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Consultar categoria por id", notes = "Consultar categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Categoria retornada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu alguma excessão")
	})
	public ResponseEntity<CategoriaResponseDTO> buscar(@Valid @PathVariable Long id) {
		CategoriaResponseDTO categoriaDTO = categoriaService.pesquisarUmDTO(id);
		if (categoriaDTO != null) {
			return ResponseEntity.ok(categoriaDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Inserir categoria", notes = "Inserir categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Categoria adicionada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")})	
	public ResponseEntity<CategoriaResponseDTO> inserir(@Valid @RequestBody CategoriaRequestDTO categoriaDTO) {
		Categoria categoria = categoriaService.insert(categoriaDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getIdCategoria()).toUri();
		
		return ResponseEntity.created(uri).body(new CategoriaResponseDTO(categoria));
		
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza dados de uma Categoria", notes = "Atualizar Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Categoria atualizada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")})
	public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO categoriaDTO) {
		if (categoriaService.idExiste(id)) {
			return ResponseEntity.ok(categoriaService.atualizarCategoria(categoriaDTO, id));		
		}		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar categoria por id", notes = "Deletar categoria")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Categoria removida"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<String> remover(@Valid @PathVariable Long id) {
		if (!categoriaService.idExiste(id)) {
			return ResponseEntity.notFound().build();
		}		
		categoriaService.remover(id);
		return ResponseEntity.ok("Categoria removido");		
	}
}