package org.serratec.backend.TrabalhoFinal.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.TrabalhoFinal.domain.Produto;
import org.serratec.backend.TrabalhoFinal.dto.ProdutoRequestDTO;
import org.serratec.backend.TrabalhoFinal.dto.ProdutoResponseDTO;

public interface ProdutoService {
	List<Produto> pesquisarTodos();
	List<ProdutoResponseDTO> findAll();
	Optional<Produto> pesquisarUm(Long id);
	Produto findById(Long id);
	Produto inserir(Produto produto);
	Produto insert(ProdutoRequestDTO produto);
	boolean idExiste(Long id);
	void remover(Long id);
	Produto atualizarProduto(Produto Produto, Long id);
	Produto update(ProdutoRequestDTO produtoCadastro, Long id);
}