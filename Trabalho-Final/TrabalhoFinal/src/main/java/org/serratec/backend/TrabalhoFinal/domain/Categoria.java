package org.serratec.backend.TrabalhoFinal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.serratec.backend.TrabalhoFinal.dto.CategoriaResponseDTO;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	@ApiModelProperty(value = "Identificador da categoria", required = true)
	private Long idCategoria;

	@NotBlank
	@Column(name = "nome_categoria")
	@ApiModelProperty(value = "Categoria do produto")
	private String nomeCategoria;

	@NotBlank
	@Column(name = "descricao_categoria")
	@ApiModelProperty(value = "Descrição da categoria")
	private String descricaoCategoria;

	
	public Categoria() {
		super();
	}

	public Categoria(CategoriaResponseDTO categoriaResponseDTO) {
		super();
		this.idCategoria = categoriaResponseDTO.getIdCategoria();
		this.nomeCategoria = categoriaResponseDTO.getNomeCategoria();
		this.descricaoCategoria = categoriaResponseDTO.getDescricaoCategoria();
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