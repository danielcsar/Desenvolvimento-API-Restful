package org.serratec.backend.TrabalhoFinal.dto;

import java.util.Date;
import java.util.List;

import org.serratec.backend.TrabalhoFinal.domain.Pedido;
import org.serratec.backend.TrabalhoFinal.domain.PedidoItem;
import org.serratec.backend.TrabalhoFinal.domain.Status;

public class PedidoResponseDTO {
	
	private Long idPedido;
	private Date dataPedido;
	private ClienteResponseDTO cliente;
	private List<PedidoItem> pedidoItens;
	private Double total;
    private Status status;
	
	public PedidoResponseDTO() {
	}
	
	public PedidoResponseDTO(Pedido pedido) {
		super();
		this.idPedido = pedido.getIdPedido();
		this.dataPedido = pedido.getDataPedido();
		this.cliente = new ClienteResponseDTO(pedido.getCliente());
		this.pedidoItens = pedido.getPedidoItens();
		this.total = pedido.getTotal();
	}

	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public ClienteResponseDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteResponseDTO cliente) {
		this.cliente = cliente;
	}
	public List<PedidoItem> getPedidoItens() {
		return pedidoItens;
	}
	public void setPedidoItens(List<PedidoItem> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}