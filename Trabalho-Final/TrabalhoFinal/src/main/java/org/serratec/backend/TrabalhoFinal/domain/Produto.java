package org.serratec.backend.TrabalhoFinal.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.serratec.backend.TrabalhoFinal.dto.ProdutoResponseDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	@ApiModelProperty(value = "Identificador do produto", required = true)
	private Long idProduto;
	
	@Column(name = "nome_produto")
	@ApiModelProperty(value = "Nome do produto", required = true)
	private String nomeProduto;

	@Column(name = "desricao_produto")
	@ApiModelProperty(value = "Descrição do produto", required = true)
	private String decricaoProduto;

	@Column(name = "qtd_estoque")
	@ApiModelProperty(value = "Quantidade em estoque do produto", required = true)
	private Integer quantidadeEstoque = 0;

	@Column(name = "data_fabricacao")
	@ApiModelProperty(value = "Data de fabricação do produto", required = true)
	private Date dataFabricacao;

	@Column(name = "valor_unitario")
	@ApiModelProperty(value = "Valor unitário do produto", required = true)
	private Double valorUnitario;	
	
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;
	
	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "pedidoItemPK.produto")
	private List<PedidoItem> pedidoItens = new ArrayList<>();
	
	public Produto() {		
	}
	
	public Produto(ProdutoResponseDTO produtoResponseDTO) {
		super();
		this.nomeProduto = produtoResponseDTO.getNomeProduto();
		this.decricaoProduto = produtoResponseDTO.getDecricaoProduto();
		this.quantidadeEstoque = produtoResponseDTO.getQuantidadeEstoque();
		this.dataFabricacao = produtoResponseDTO.getDataFabricacao();
		this.valorUnitario = produtoResponseDTO.getValorUnitario();
		this.categoria = new Categoria(produtoResponseDTO.getCategoria());
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long id) {
		this.idProduto = id;
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
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
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
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<PedidoItem> getPedidoItens() {
		return pedidoItens;
	}
	public void setPedidoItens(List<PedidoItem> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}
}