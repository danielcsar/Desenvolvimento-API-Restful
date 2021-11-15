package org.serratec.backend.TrabalhoFinal.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "pedido_item")
public class PedidoItem {
	
	@JsonIgnore
	@EmbeddedId
	private PedidoItemPk pedidoItemPK;
	
	private Long id;

	@Column(name = "qtd_produto")
	@ApiModelProperty(value = "Quantidade de produto", required = true)
	private Integer qtdProduto;
	
	@Column(name = "preco_venda")
	@ApiModelProperty(value = "Preço de venda", required = true)
	private Double precoVenda;	
	
	public PedidoItem(PedidoItem pedidoItemRequestDTO) {
		super();
		pedidoItemPK = new PedidoItemPk();
		pedidoItemPK.setPedido(pedidoItemRequestDTO.getPedidoItemPK().getPedido());
		pedidoItemPK.setProduto(pedidoItemRequestDTO.getPedidoItemPK().getProduto());
		this.id = pedidoItemRequestDTO.getPedidoItemPK().getProduto().getIdProduto();
		this.qtdProduto = pedidoItemRequestDTO.getQtdProduto();
		this.precoVenda = pedidoItemRequestDTO.getPrecoVenda();
	}	
	
	public PedidoItem() {
	}
	
	public PedidoItemPk getPedidoItemPK() {
		return pedidoItemPK;
	}
	public void setPedidoItemPK(PedidoItemPk pedidoItemPK) {
		this.pedidoItemPK = pedidoItemPK;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQtdProduto() {
		return qtdProduto;
	}
	public void setQtdProduto(Integer qtdProduto) {
		this.qtdProduto = qtdProduto;
	}
	public Double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}
	@Override
	public String toString() {
		return "PedidoItem \n[Código Produto = " + pedidoItemPK.getProduto().getIdProduto() + 
				"\n Quantidade = " + qtdProduto + "\n Preço Venda = " + precoVenda
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(pedidoItemPK, precoVenda, qtdProduto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoItem other = (PedidoItem) obj;
		return Objects.equals(pedidoItemPK, other.pedidoItemPK) && Objects.equals(precoVenda, other.precoVenda)
				&& Objects.equals(qtdProduto, other.qtdProduto);
	}	
}