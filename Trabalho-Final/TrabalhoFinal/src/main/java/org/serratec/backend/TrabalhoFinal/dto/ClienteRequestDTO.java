package org.serratec.backend.TrabalhoFinal.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

public class ClienteRequestDTO {
	
	@Size(max = 60, message = "Limite de 60 caracteres")
	@NotBlank(message = "O nome do cliente não pode estar vazio")
	private String nomeCliente;
	
	@Email(message = "Email Inválido!")
	private String emailCliente;
	
	@Size(max = 11)	
	@NotNull(message= "O cpf não pode ser nulo")
	@CPF(message = "Digite um CPF válido")
	private String cpfCliente;
	
	@PastOrPresent(message = "Data Inválida")
	private Date nascimentoCliente;

	private String cep;
	
	public ClienteRequestDTO() {
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
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public Date getNascimentoCliente() {
		return nascimentoCliente;
	}
	public void setNascimentoCliente(Date nascimentoCliente) {
		this.nascimentoCliente = nascimentoCliente;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}	
}	