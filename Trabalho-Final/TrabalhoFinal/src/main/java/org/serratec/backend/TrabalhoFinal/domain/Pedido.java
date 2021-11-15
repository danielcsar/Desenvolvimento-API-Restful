package org.serratec.backend.TrabalhoFinal.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.serratec.backend.TrabalhoFinal.dto.PedidoRequestDTO;
import org.serratec.backend.TrabalhoFinal.service.impl.ClienteServiceImpl;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	@ApiModelProperty(value = "Identificador do pedido", required = true)
	private Long idPedido;
	
	@Column(name = "data_pedido")
	@ApiModelProperty(value = "Data do pedido", required = true)
	private Date dataPedido;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente Cliente;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pedidoItemPK.pedido")
	private List<PedidoItem> pedidoItens = new ArrayList<>();
	
	@Column(name = "total")
	private Double total;
	
	@NotNull(message = "campo obrigatório")
	@Enumerated(EnumType.ORDINAL)
    private Status status;
	
	public Pedido() {		
	};
	
	public Pedido(PedidoRequestDTO pedido) {
		super();
		this.dataPedido = pedido.getDataPedido();
		Cliente cliente = new ClienteServiceImpl().findbyID(pedido.getIdCliente());
		this.Cliente = cliente;
		this.pedidoItens = pedido.getPedidoItens();
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
	public Cliente getCliente() {
		return Cliente;
	}
	public void setCliente(Cliente cliente) {
		Cliente = cliente;
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

	@Override
	public String toString() {
		return "Pedido \n[Número do Pedido = " + idPedido + "\n Data do Pedido = " 
				+ dataPedido + "\n Cliente = " + Cliente + "\n Lista de Itens = "
				+ pedidoItens + "\n Total do Pedido = " + total + "]";
	}	
}