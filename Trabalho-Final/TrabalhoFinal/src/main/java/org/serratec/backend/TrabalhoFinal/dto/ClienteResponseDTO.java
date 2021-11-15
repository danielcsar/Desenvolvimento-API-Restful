package org.serratec.backend.TrabalhoFinal.dto;

import org.serratec.backend.TrabalhoFinal.domain.Cliente;

public class ClienteResponseDTO {
	
	private Long idCliente;
	private String nomeCliente;
	private String emailCliente;
	private EnderecoResponseDTO endereco;
	
	public ClienteResponseDTO() {
	}
	
	public ClienteResponseDTO(Cliente cliente) {
		super();
		this.idCliente = cliente.getIdCliente();
		this.nomeCliente = cliente.getNomeCliente();
		this.emailCliente = cliente.getEmailCliente();
		this.endereco = new EnderecoResponseDTO(cliente.getEndereco());
	}
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public EnderecoResponseDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoResponseDTO endereco) {
		this.endereco = endereco;
	}
}