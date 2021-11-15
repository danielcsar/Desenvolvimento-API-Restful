package org.serratec.backend.TrabalhoFinal.repository;

import org.serratec.backend.TrabalhoFinal.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}