package org.serratec.backend.TrabalhoFinal.service.impl;

import org.serratec.backend.TrabalhoFinal.domain.Endereco;
import org.serratec.backend.TrabalhoFinal.dto.EnderecoResponseDTO;
import org.serratec.backend.TrabalhoFinal.repository.EnderecoRepository;
import org.serratec.backend.TrabalhoFinal.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public Endereco buscarCep(String cep) throws HttpClientErrorException{
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://viacep.com.br/ws/" + cep + "/json";
		Endereco enderecoViaCep = restTemplate.getForObject(uri, Endereco.class);
		String cepSemTraco = enderecoViaCep.getCep().replaceAll("-", "");
		enderecoViaCep.setCep(cepSemTraco);		
		return enderecoViaCep;
	}
		
	@Override
	public EnderecoResponseDTO inserirCep(Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		return new EnderecoResponseDTO(endereco);
	}	
}