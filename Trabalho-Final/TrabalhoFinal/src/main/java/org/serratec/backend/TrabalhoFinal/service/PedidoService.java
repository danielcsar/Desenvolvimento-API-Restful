package org.serratec.backend.TrabalhoFinal.service;

import java.util.List;

import org.serratec.backend.TrabalhoFinal.domain.Pedido;
import org.serratec.backend.TrabalhoFinal.dto.PedidoRequestDTO;
import org.serratec.backend.TrabalhoFinal.dto.PedidoResponseDTO;



public interface PedidoService {
	List<Pedido> pesquisarTodos();
	List<PedidoResponseDTO> findAll();
	PedidoResponseDTO pesquisarUm(Long idCliente);
	Pedido inserir(Pedido pedido);
	PedidoResponseDTO insert(PedidoRequestDTO pedidoRequestDTO);
	List<Pedido> inserirVarios(List<Pedido> pedido);
	boolean idExiste(Long id);
	void remover(Long id);
	Double calcularTotalPedido(PedidoRequestDTO pedido);
	Pedido update(PedidoRequestDTO pedidoRequestDTO, Long id);
}