package org.serratec.backend.TrabalhoFinal.service;

import java.util.Set;

import org.serratec.backend.TrabalhoFinal.domain.PedidoItem;
import org.serratec.backend.TrabalhoFinal.dto.PedidoRequestDTO;

public interface PedidoItemService {
	Set<PedidoItem> criarListaPedidoItem(Set<PedidoRequestDTO> listaPedidoItem);
}
