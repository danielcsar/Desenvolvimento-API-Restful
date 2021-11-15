package org.serratec.backend.TrabalhoFinal.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	@ApiModelProperty(value = "Identificador do cliente", required = true)
	private Long idCliente;

	@Column(name = "nome_cliente")
	@ApiModelProperty(value = "Nome do cliente", required = true)
	private String nomeCliente;

	@Column(name = "email_cliente")
	@ApiModelProperty(value = "E-mail do cliente", required = true)
	private String emailCliente;
	
	@Column(name = "cpf_cliente")
	@ApiModelProperty(value = "CPF do cliente", required = true)
	private String cpfCliente;

	@Column(name = "nascimento_cliente")
	@ApiModelProperty(value = "Data de nascimento do cliente", required = true)
	private Date nascimentoCliente;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

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
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "Cliente \n[Código = " + idCliente + "\n Nome = " + nomeCliente + "\n Email = " + emailCliente
				+ "\n CPF = " + cpfCliente + "\n Data de Nascimento = " + nascimentoCliente + "\n Endereco = " + endereco
				+ "]";
	}
}