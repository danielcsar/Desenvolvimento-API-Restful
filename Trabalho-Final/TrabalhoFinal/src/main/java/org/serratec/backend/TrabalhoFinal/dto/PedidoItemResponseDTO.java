package org.serratec.backend.TrabalhoFinal.dto;

import org.serratec.backend.TrabalhoFinal.domain.PedidoItem;

public class PedidoItemResponseDTO {
	
	private int qteProduto;
	private Double precoVenda;
	
	public PedidoItemResponseDTO() {
	}

	public PedidoItemResponseDTO(PedidoItem pedidoItem) {
		super();
		this.qteProduto = pedidoItem.getQtdProduto();
		this.precoVenda = pedidoItem.getPrecoVenda();
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