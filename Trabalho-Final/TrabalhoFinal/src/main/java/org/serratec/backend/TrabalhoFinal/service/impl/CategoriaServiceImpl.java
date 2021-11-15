package org.serratec.backend.TrabalhoFinal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.TrabalhoFinal.domain.Categoria;
import org.serratec.backend.TrabalhoFinal.repository.CategoriaRepository;
import org.serratec.backend.TrabalhoFinal.dto.CategoriaRequestDTO;
import org.serratec.backend.TrabalhoFinal.dto.CategoriaResponseDTO;
import org.serratec.backend.TrabalhoFinal.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> pesquisarTodos() {
		return categoriaRepository.findAll();
	}	

	@Override
	public List<CategoriaResponseDTO> findAll() {
		List<Categoria> categorias = pesquisarTodos();
		List<CategoriaResponseDTO> categoriasDTO = new ArrayList<CategoriaResponseDTO>();
		
		for (Categoria c : categorias) {
			CategoriaResponseDTO categoriaDTO = new CategoriaResponseDTO(c);
			categoriasDTO.add(categoriaDTO);
			}
		
		return categoriasDTO;
	}

	@Override
	public Optional<Categoria> pesquisarUm(Long idCategoria) {
		return categoriaRepository.findById(idCategoria);
	}
	
	@Override
	public CategoriaResponseDTO pesquisarUmDTO(Long idCategoria) {
		CategoriaResponseDTO categoriaDTO = new CategoriaResponseDTO();
		
		categoriaDTO.setIdCategoria(categoriaRepository.findById(idCategoria).get().getIdCategoria());
		categoriaDTO.setNomeCategoria(categoriaRepository.findById(idCategoria).get().getNomeCategoria());
		categoriaDTO.setDescricaoCategoria(categoriaRepository.findById(idCategoria).get().getDescricaoCategoria());
		
		return categoriaDTO;
	}
	
	@Override
	public Categoria inserir(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@Override
	public Categoria insert(CategoriaRequestDTO categoriaRequestDTO) {
		Categoria categoria = new Categoria();
		categoria.setNomeCategoria(categoriaRequestDTO.getNomeCategoria());
		categoria.setDescricaoCategoria(categoriaRequestDTO.getDescricaoCategoria());
		categoriaRepository.save(categoria);
		return categoria;
	}
	
	@Override
	public CategoriaResponseDTO atualizarCategoria(CategoriaRequestDTO categoriaRequestDTO, Long id) {
		Categoria categoria = categoriaRepository.getById(id);
		
		categoria.setNomeCategoria(categoriaRequestDTO.getNomeCategoria());
		categoria.setDescricaoCategoria(categoriaRequestDTO.getDescricaoCategoria());
		
		categoriaRepository.save(categoria);
		
		return new CategoriaResponseDTO(categoria);
	}

	@Override
	public boolean idExiste(Long id) {
		return categoriaRepository.existsById(id);
	}

	@Override
	public void remover(Long id) {
		categoriaRepository.deleteById(id);
	}	
}
