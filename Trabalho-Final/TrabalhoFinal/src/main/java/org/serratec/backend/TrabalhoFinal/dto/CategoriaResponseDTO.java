package org.serratec.backend.TrabalhoFinal.dto;

import java.util.List;

import org.serratec.backend.TrabalhoFinal.domain.Categoria;
import org.serratec.backend.TrabalhoFinal.domain.Produto;

public class CategoriaResponseDTO {

	private Long idCategoria;
	private String nomeCategoria;
	private String descricaoCategoria;
	
	public CategoriaResponseDTO() {
	}
	
	public CategoriaResponseDTO(String nomeCategoria, String descricaoCategoria, List<Produto> produtos) {
		super();
		this.nomeCategoria = nomeCategoria;
		this.descricaoCategoria = descricaoCategoria;
	}
	
	public CategoriaResponseDTO(Categoria categoria) {
		super();
		this.idCategoria = categoria.getIdCategoria();
		this.nomeCategoria = categoria.getNomeCategoria();
		this.descricaoCategoria = categoria.getDescricaoCategoria();
	}

	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
}
