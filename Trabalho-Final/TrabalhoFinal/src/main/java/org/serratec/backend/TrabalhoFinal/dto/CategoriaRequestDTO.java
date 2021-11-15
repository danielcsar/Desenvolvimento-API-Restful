package org.serratec.backend.TrabalhoFinal.dto;

import java.util.List;

import javax.validation.constraints.Size;

import org.serratec.backend.TrabalhoFinal.domain.Produto;

public class CategoriaRequestDTO {
	
	@Size(max = 40, message = "Limite de 40 caracteres.")
	private String nomeCategoria;
	
	@Size(max = 200, message = "Limite de 200 caracteres.")
	private String descricaoCategoria;
	
	public CategoriaRequestDTO() {
	}
	
	public CategoriaRequestDTO(String nomeCategoria, String descricaoCategoria, List<Produto> produtos) {
		super();
		this.nomeCategoria = nomeCategoria;
		this.descricaoCategoria = descricaoCategoria;
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