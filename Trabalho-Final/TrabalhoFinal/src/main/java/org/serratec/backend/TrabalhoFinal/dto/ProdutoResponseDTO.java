package org.serratec.backend.TrabalhoFinal.dto;

import java.util.Date;

import org.serratec.backend.TrabalhoFinal.domain.Produto;

public class ProdutoResponseDTO {
	
	private Long idProduto;
	private String nomeProduto;
	private String decricaoProduto;
	private int quantidadeEstoque;
	private Date dataFabricacao;
	private Double valorUnitario;
	private CategoriaResponseDTO categoria;
	
	public ProdutoResponseDTO() {
	}
	
	public ProdutoResponseDTO(Produto produto) {
		super();
		this.idProduto = produto.getIdProduto();
		this.nomeProduto = produto.getNomeProduto();
		this.decricaoProduto = produto.getDecricaoProduto();
		this.quantidadeEstoque = produto.getQuantidadeEstoque();
		this.dataFabricacao = produto.getDataFabricacao();
		this.valorUnitario = produto.getValorUnitario();
		this.categoria = new CategoriaResponseDTO(produto.getCategoria());
	}


	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
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
	public CategoriaResponseDTO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaResponseDTO categoria) {
		this.categoria = categoria;
	}
}