package org.serratec.backend.TrabalhoFinal.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.TrabalhoFinal.domain.Categoria;
import org.serratec.backend.TrabalhoFinal.dto.CategoriaRequestDTO;
import org.serratec.backend.TrabalhoFinal.dto.CategoriaResponseDTO;

public interface CategoriaService {

	List<Categoria> pesquisarTodos();
	List<CategoriaResponseDTO> findAll();
	Optional<Categoria> pesquisarUm(Long idCliente);
	CategoriaResponseDTO pesquisarUmDTO(Long idCliente);
	Categoria inserir(Categoria categoria);
	Categoria insert(CategoriaRequestDTO categoria);
	CategoriaResponseDTO atualizarCategoria(CategoriaRequestDTO categoriaRequestDTO, Long id);
	boolean idExiste(Long id);
	void remover(Long id);
}