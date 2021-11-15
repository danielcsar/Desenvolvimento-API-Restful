package org.serratec.backend.TrabalhoFinal.dto;

import org.serratec.backend.TrabalhoFinal.domain.PedidoItem;
import org.serratec.backend.TrabalhoFinal.domain.PedidoItemPk;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PedidoItemRequestDTO {
	
	@JsonIgnore
	private PedidoItemPk pedidoItemPK;
	private Long idProduto;
	private int qteProduto;
	private Double precoVenda;	
	
	public PedidoItemRequestDTO() {
	}
	
	public PedidoItemRequestDTO(PedidoItem pedidoItem) {
		super();
		this.idProduto = pedidoItem.getPedidoItemPK().getProduto().getIdProduto();
		this.qteProduto = pedidoItem.getQtdProduto();
		this.precoVenda = pedidoItem.getPrecoVenda();
	}

	public PedidoItemPk getPedidoItemPK() {
		return pedidoItemPK;
	}
	public void setPedidoItemPK(PedidoItemPk pedidoItemPK) {
		this.pedidoItemPK = pedidoItemPK;
	}
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public int getQteProduto() {
		return qteProduto;
	}
	public void setQteProduto(int qteProduto) {
		this.qteProduto = qteProduto;
	}
	public Double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}
}