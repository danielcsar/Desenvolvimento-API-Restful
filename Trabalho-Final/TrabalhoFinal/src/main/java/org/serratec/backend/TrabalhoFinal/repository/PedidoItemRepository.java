package org.serratec.backend.TrabalhoFinal.repository;

import org.serratec.backend.TrabalhoFinal.domain.PedidoItem;
import org.serratec.backend.TrabalhoFinal.domain.PedidoItemPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository  extends JpaRepository<PedidoItem, PedidoItemPk>{

}