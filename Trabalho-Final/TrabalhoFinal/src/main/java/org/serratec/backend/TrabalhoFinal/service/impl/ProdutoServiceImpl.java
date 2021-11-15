package org.serratec.backend.TrabalhoFinal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.TrabalhoFinal.domain.Produto;
import org.serratec.backend.TrabalhoFinal.dto.CategoriaResponseDTO;
import org.serratec.backend.TrabalhoFinal.dto.ProdutoRequestDTO;
import org.serratec.backend.TrabalhoFinal.dto.ProdutoResponseDTO;
import org.serratec.backend.TrabalhoFinal.repository.CategoriaRepository;
import org.serratec.backend.TrabalhoFinal.repository.ProdutoRepository;
import org.serratec.backend.TrabalhoFinal.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<Produto> pesquisarTodos() {
		return produtoRepository.findAll();
	}

	@Override
	public List<ProdutoResponseDTO> findAll() {
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoResponseDTO> produtoResponseDTO = new ArrayList<ProdutoResponseDTO>();

		for (Produto p : produtos) {
			produtoResponseDTO.add(new ProdutoResponseDTO(p));
		}
		return produtoResponseDTO;
	}

	@Override
	public Optional<Produto> pesquisarUm(Long id) {
		return produtoRepository.findById(id);
	}

	@Override
	public Produto findById(Long id) {
		ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();

		if (produtoRepository.findById(id).isPresent()) {
			produtoResponseDTO.setIdProduto(produtoRepository.findById(id).get().getIdProduto());
			produtoResponseDTO.setNomeProduto(produtoRepository.findById(id).get().getNomeProduto());
			produtoResponseDTO.setDecricaoProduto(produtoRepository.findById(id).get().getDecricaoProduto());
			produtoResponseDTO.setQuantidadeEstoque(produtoRepository.findById(id).get().getQuantidadeEstoque());
			produtoResponseDTO.setValorUnitario(produtoRepository.findById(id).get().getValorUnitario());
			produtoResponseDTO.setCategoria(new CategoriaResponseDTO(produtoRepository.findById(id).get().getCategoria()));
			return new Produto(produtoResponseDTO);
		}
		return null;
	}

	@Override
	public Produto inserir(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Produto insert(ProdutoRequestDTO produtoRequestDTO) {
		Produto produto = new Produto();
		
		produto.setNomeProduto(produtoRequestDTO.getNomeProduto());		
		produto.setDecricaoProduto(produtoRequestDTO.getDecricaoProduto());
		produto.setDataFabricacao(produtoRequestDTO.getDataFabricacao());
		produto.setQuantidadeEstoque(produtoRequestDTO.getQuantidadeEstoque());
		produto.setValorUnitario(produtoRequestDTO.getValorUnitario());
		produto.setCategoria(categoriaRepository.getById(produtoRequestDTO.getIdCategoria()));
		produtoRepository.save(produto);				
		return produto;
	}

	@Override
	public boolean idExiste(Long id) {
		return produtoRepository.existsById(id);
	}

	@Override
	public void remover(Long id) {
		produtoRepository.deleteById(id);
	}

	@Override
	public Produto atualizarProduto(Produto Produto, Long id) {
		return null;
	}

	@Override
	public Produto update(ProdutoRequestDTO produtoRequestDTO, Long id) {
		Produto produto = produtoRepository.getById(id);
		
		produto.setNomeProduto(produtoRequestDTO.getNomeProduto());		
		produto.setDecricaoProduto(produtoRequestDTO.getDecricaoProduto());
		produto.setDataFabricacao(produtoRequestDTO.getDataFabricacao());
		produto.setQuantidadeEstoque(produtoRequestDTO.getQuantidadeEstoque());
		produto.setValorUnitario(produtoRequestDTO.getValorUnitario());
		produto.setCategoria(categoriaRepository.getById(produtoRequestDTO.getIdCategoria()));
		produtoRepository.save(produto);				
		return produto;
	}
}