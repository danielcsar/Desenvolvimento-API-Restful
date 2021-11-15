package org.serratec.backend.TrabalhoFinal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.serratec.backend.TrabalhoFinal.domain.Pedido;
import org.serratec.backend.TrabalhoFinal.domain.PedidoItem;
import org.serratec.backend.TrabalhoFinal.domain.Status;

public class PedidoRequestDTO {
	
	private Date dataPedido;	
	private Long idCliente;	
	private List<PedidoItem> pedidoItens = new ArrayList<>();
	private Status status;
	
	public PedidoRequestDTO() {
	}
	
	public PedidoRequestDTO(Pedido pedido) {
		super();
		this.dataPedido = pedido.getDataPedido();
		this.idCliente = pedido.getCliente().getIdCliente();
	}

	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public List<PedidoItem> getPedidoItens() {
		return pedidoItens;
	}
	public void setPedidoItens(List<PedidoItem> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}	
}