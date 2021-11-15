package org.serratec.backend.TrabalhoFinal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.TrabalhoFinal.config.EmailConfig;
import org.serratec.backend.TrabalhoFinal.domain.Pedido;
import org.serratec.backend.TrabalhoFinal.domain.PedidoItem;
import org.serratec.backend.TrabalhoFinal.domain.PedidoItemPk;
import org.serratec.backend.TrabalhoFinal.domain.Produto;
import org.serratec.backend.TrabalhoFinal.dto.ClienteResponseDTO;
import org.serratec.backend.TrabalhoFinal.dto.PedidoRequestDTO;
import org.serratec.backend.TrabalhoFinal.dto.PedidoResponseDTO;
import org.serratec.backend.TrabalhoFinal.repository.PedidoItemRepository;
import org.serratec.backend.TrabalhoFinal.repository.PedidoRepository;
import org.serratec.backend.TrabalhoFinal.service.ClienteService;
import org.serratec.backend.TrabalhoFinal.service.PedidoService;
import org.serratec.backend.TrabalhoFinal.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	
	@Autowired
	private ClienteService clienteService;	
	
	@Autowired
	private ProdutoService produtoService;	
	
	@Autowired
    private EmailConfig email;

	@Override
	public List<Pedido> pesquisarTodos() {
		return pedidoRepository.findAll();
	}

	@Override
	public List<PedidoResponseDTO> findAll() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoResponseDTO> pedidosResponse = new ArrayList<PedidoResponseDTO>();
		
		for(Pedido pedido : pedidos) {
			pedidosResponse.add(new PedidoResponseDTO(pedido));
		}
		return pedidosResponse;
	}

	@Override
	public PedidoResponseDTO pesquisarUm(Long idPedido) {
		PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO();
		
		if(pedidoRepository.findById(idPedido).isPresent()) {
			pedidoResponseDTO.setDataPedido(pedidoRepository.findById(idPedido).get().getDataPedido());
			pedidoResponseDTO.setCliente(new ClienteResponseDTO(pedidoRepository.findById(idPedido).get().getCliente()));
			pedidoResponseDTO.setPedidoItens(pedidoRepository.findById(idPedido).get().getPedidoItens());
			pedidoResponseDTO.setTotal(pedidoRepository.findById(idPedido).get().getTotal());
			return pedidoResponseDTO;
		}
		return null;
	}

	@Override
	public Pedido inserir(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public PedidoResponseDTO insert(PedidoRequestDTO pedidoRequestDTO) {
		Pedido pedido = new Pedido();
		
		pedido.setDataPedido(pedidoRequestDTO.getDataPedido());
		pedido.setCliente(clienteService.findbyID(pedidoRequestDTO.getIdCliente()));
		pedido.setPedidoItens(criarListaItens(pedidoRequestDTO));
		pedido.setTotal(calcularTotalPedido(pedidoRequestDTO));		
		
		pedidoRepository.save(pedido);		
		
		email.sendEmail(clienteService.findbyID(pedidoRequestDTO.getIdCliente()).getEmailCliente(), "Novo Pedido" , pedido.toString());

		return new PedidoResponseDTO(pedido);		
	}

	@Override
	public List<Pedido> inserirVarios(List<Pedido> listaPedidos) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
	
		for(Pedido p : listaPedidos) {
			pedidos.add(p);
			pedidoRepository.save(p);
		}
		return pedidos;
	}

	@Override
	public boolean idExiste(Long id) {
		return pedidoRepository.existsById(id);
	}

	@Override
	public void remover(Long id) {
		pedidoRepository.deleteById(id);		
	}
		
	@Override
	public Double calcularTotalPedido(PedidoRequestDTO pedidoRequestDTO) {
		Double total = 0.0;
		List<PedidoItem> lista = pedidoRequestDTO.getPedidoItens(); // stream().map(i -> new PedidoItem(i)).collect(Collectors.toList());
		
		for(PedidoItem pedidoItem : lista) {
			total += pedidoItem.getPrecoVenda() * pedidoItem.getQtdProduto();
		}
		return total;
	}
	
	@Override
	public Pedido update(PedidoRequestDTO pedidoRequestDTO, Long id) {
		Pedido pedido = pedidoRepository.getById(id);
		
		pedido.setDataPedido(pedidoRequestDTO.getDataPedido());
		pedido.setCliente(clienteService.findbyID(pedidoRequestDTO.getIdCliente()));		
		pedido.setTotal(calcularTotalPedido(pedidoRequestDTO));
		pedidoRepository.save(pedido);
		return pedido;
	}
	
	public List<PedidoItem> criarListaItens(PedidoRequestDTO pedidoRequestDTO){
		List<PedidoItem> lista = new ArrayList<>();
		
		for(PedidoItem pedido : pedidoRequestDTO.getPedidoItens()) {
			PedidoItem pedidoItem = new PedidoItem();
			pedidoItem.setId(pedido.getPedidoItemPK().getProduto().getIdProduto());
			
			Produto produto = produtoService.findById(pedido.getPedidoItemPK().getProduto().getIdProduto());			
			pedidoItem.setPedidoItemPK(new PedidoItemPk(new Pedido(pedidoRequestDTO),produto));
			pedidoItem.setPrecoVenda(pedido.getPrecoVenda());
			pedidoItem.setQtdProduto(pedido.getQtdProduto());
			pedidoItemRepository.save(pedidoItem);
			
			lista.add(pedidoItem);			
		}		
		return lista;
	}
}