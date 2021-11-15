package org.serratec.backend.TrabalhoFinal.dto;

import org.serratec.backend.TrabalhoFinal.domain.Endereco;

public class EnderecoResponseDTO {

	private String logradouro;
	private String bairro;   
	private String cep;
	private String localidade;
	private String uf;
	
	public EnderecoResponseDTO() {
	}

	public EnderecoResponseDTO(Endereco endereco) {
		super();
		this.logradouro = endereco.getLogradouro();
		this.bairro = endereco.getBairro();
		this.cep = endereco.getCep();
		this.localidade = endereco.getLocalidade();
		this.uf = endereco.getUf();
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return localidade;
	}
	public void setCidade(String cidade) {
		this.localidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}	
}
