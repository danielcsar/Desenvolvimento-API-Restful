package org.serratec.backend.TrabalhoFinal.dto;

import java.util.Date;

import org.serratec.backend.TrabalhoFinal.domain.Produto;

public class ProdutoRequestDTO {
	private String nomeProduto;
	private String decricaoProduto;
	private int quantidadeEstoque;
	private Date dataFabricacao;
	private Double valorUnitario;
	private Long idCategoria;
	
	public ProdutoRequestDTO() {
	}
	
	public ProdutoRequestDTO(Produto produto) {
		super();
		this.nomeProduto = produto.getNomeProduto();
		this.decricaoProduto = produto.getDecricaoProduto();
		this.quantidadeEstoque = produto.getQuantidadeEstoque();
		this.dataFabricacao = produto.getDataFabricacao();
		this.valorUnitario = produto.getValorUnitario();
		this.idCategoria = produto.getCategoria().getIdCategoria();
	}

	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getDecricaoProduto() {
		return decricaoProduto;
	}
	public void setDecricaoProduto(String decricaoProduto) {
		this.decricaoProduto = decricaoProduto;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public Date getDataFabricacao() {
		return dataFabricacao;
	}
	public void setDataFabricacao(Date dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}	
}
